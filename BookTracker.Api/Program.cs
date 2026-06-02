using BookTracker.Api.Data;
using BookTracker.Api.DTOs;
using BookTracker.Api.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using System.Security.Claims;
using System.IdentityModel.Tokens.Jwt;
using Microsoft.OpenApi.Models;
using MongoDB.Driver;
using System;

var builder = WebApplication.CreateBuilder(args);


#region Connection Strings

var mongoConnectionString =
    builder.Configuration["MongoDb:ConnectionString"];

var mongoDatabaseName =
    builder.Configuration["MongoDb:DatabaseName"];

builder.Services.AddSingleton<IMongoClient>(
    _ => new MongoClient(mongoConnectionString));

builder.Services.AddSingleton(sp =>
{
    var client = sp.GetRequiredService<IMongoClient>();

    return client.GetDatabase(mongoDatabaseName);
});


var connectionString = builder.Configuration.GetConnectionString("Default");

builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseMySql(connectionString,
    Microsoft.EntityFrameworkCore.ServerVersion.AutoDetect(connectionString)));

#endregion

#region JWT
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(options =>
{
    options.SwaggerDoc("v1", new OpenApiInfo
    {
        Title = "BookTracker API",
        Version = "v1"
    });

    options.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
    {
        Name = "Authorization",
        Type = SecuritySchemeType.Http,
        Scheme = "bearer",
        BearerFormat = "JWT",
        In = ParameterLocation.Header,
        Description = "Digite o token JWT"
    });

    options.AddSecurityRequirement(new OpenApiSecurityRequirement
    {
        {
            new OpenApiSecurityScheme
            {
                Reference = new OpenApiReference
                {
                    Type = ReferenceType.SecurityScheme,
                    Id = "Bearer"
                }
            },
            Array.Empty<string>()
        }
    });
});

var jwtKey = builder.Configuration["Jwt:Key"];

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidateAudience = true,
            ValidateLifetime = true,
            ValidateIssuerSigningKey = true,

            ValidIssuer = builder.Configuration["Jwt:Issuer"],
            ValidAudience = builder.Configuration["Jwt:Audience"],

            IssuerSigningKey = new SymmetricSecurityKey(
                Encoding.UTF8.GetBytes(jwtKey))
        };
    });

builder.Services.AddAuthorization();

#endregion

var app = builder.Build(); 

#region ENDPOINTS

#region Testes

app.MapGet("/test-db", async (AppDbContext db) =>
{
    var usuarios = await db.Usuarios.ToListAsync();
    return Results.Ok(usuarios);
});

app.MapGet("/mongo-test", async (
    IMongoDatabase database) =>
{
    var collections =
        await database.ListCollectionNames().ToListAsync();

    return Results.Ok(collections);
});

#endregion

#region Usuario 

app.MapPost("/login", async (
    LoginDTO dto, AppDbContext db, IConfiguration config) =>
    {
        var usuario = await db.Usuarios
            .FirstOrDefaultAsync(u =>
                u.Email == dto.Email &&
                u.Senha == dto.Senha);

        if (usuario == null)
            return Results.Unauthorized();

        var claims = new[]
        {
            new Claim(ClaimTypes.Name, usuario.Nome),
            new Claim(ClaimTypes.Email, usuario.Email),
            new Claim("Id", usuario.Id_Usuario.ToString())
        };

        var key = new SymmetricSecurityKey(
            Encoding.UTF8.GetBytes(config["Jwt:Key"]));

        var creds = new SigningCredentials(
            key,
            SecurityAlgorithms.HmacSha256);

        var token = new JwtSecurityToken(
            issuer: config["Jwt:Issuer"],
            audience: config["Jwt:Audience"],
            claims: claims,
            expires: DateTime.Now.AddHours(2),
            signingCredentials: creds
        );

        var tokenString = new JwtSecurityTokenHandler()
            .WriteToken(token);

        return Results.Ok(new
        {
            token = tokenString
        });
    });

#endregion

#region Biblioteca

app.MapPost("/api/library/", async (
    AddBookToLibraryRequest request, IMongoDatabase database, AppDbContext context) =>
    {
        var collection =
            database.GetCollection<UserLibrary>("userLibraries");

        var existing = await collection
            .Find(x =>
                x.UserId == request.UserId &&
                x.BookId == request.BookId)
                .FirstOrDefaultAsync();

        if (existing is not null)
            return Results.Conflict("Livro já está na biblioteca.");

        // Verificando se o livro e o usuário existem no banco relacional    
        var livroExiste = await context.Livros
        .AnyAsync(x => x.Id_Livro == request.BookId);

        if (!livroExiste)
            return Results.NotFound("Livro não encontrado.");

        // Verificando se o usuário existe no banco relacional
        var usuarioExiste = await context.Usuarios
        .AnyAsync(x => x.Id_Usuario == request.UserId);

        if (!usuarioExiste)
            return Results.NotFound("Usuário não encontrado.");


        var userLibrary = new UserLibrary
        {
            UserId = request.UserId,
            BookId = request.BookId,
            Status = "want_to_read",
            CurrentPage = 0,
            Rating = null,
            Tags = [],
            StartedAt = null,
            FinishedAt = null
        };

        await collection.InsertOneAsync(userLibrary);

        return Results.Ok(userLibrary);
    }).RequireAuthorization();;

app.MapGet("/api/library/{userId}/books", async (
    int userId, IMongoDatabase database, AppDbContext context) =>
    {
        var collection =
            database.GetCollection<UserLibrary>("userLibraries");

        var userLibrary = await collection
            .Find(x => x.UserId == userId)
            .ToListAsync();

        if (!userLibrary.Any())
        {
            return Results.Ok(new List<BibliotecaLivroResponseDTO>());
        }

        var bookIds = userLibrary
            .Select(x => x.BookId)
            .ToList();

        var livros = await context.Livros
            .Include(x => x.Autor)
            .Where(x => bookIds.Contains(x.Id_Livro))
            .ToListAsync();

        var response = livros
            .Select(livro =>
            {
                var libraryItem = userLibrary
                    .First(x => x.BookId == livro.Id_Livro);

                return new BibliotecaLivroResponseDTO
                {
                    LivroId = livro.Id_Livro,
                    Titulo = livro.Titulo,
                    Autor = livro.Autor.Nome,
                    TotalPaginas = livro.Total_Paginas,

                    Status = libraryItem.Status,
                    CurrentPage = libraryItem.CurrentPage
                };
            })
            .ToList();

        return Results.Ok(response);
    }).RequireAuthorization();;

app.MapPatch("/api/library/{userId}/{bookId}", async (
    int userId, int bookId, UpdateReadingProgressDTO request, IMongoDatabase database, AppDbContext context) =>
    {
        var collection =
            database.GetCollection<UserLibrary>("userLibraries");

        var libraryBook = await collection
            .Find(x =>
                x.UserId == userId &&
                x.BookId == bookId)
            .FirstOrDefaultAsync();

        if (libraryBook is null)
            return Results.NotFound();

        var livro = await context.Livros
        .FirstOrDefaultAsync(x => x.Id_Livro == bookId);

        if (livro is null)
        return Results.NotFound("Livro não encontrado.");

        libraryBook.CurrentPage = request.CurrentPage;

        if (request.CurrentPage > 0)
        {
            libraryBook.Status = "reading";

            if (libraryBook.StartedAt is null)
                libraryBook.StartedAt = DateTime.UtcNow;
        }
        if (request.CurrentPage >= livro.Total_Paginas)
        {
            libraryBook.Status = "completed";

            if (libraryBook.FinishedAt is null)
                libraryBook.FinishedAt = DateTime.UtcNow;
        }

        if (request.CurrentPage > livro.Total_Paginas)
            return Results.BadRequest($"O livro possui apenas {livro.Total_Paginas} páginas.");

        await collection.ReplaceOneAsync(
            x =>
                x.UserId == userId &&
                x.BookId == bookId,
            libraryBook);

        return Results.Ok(libraryBook);
    }).RequireAuthorization();;

app.MapPatch("/api/library/{userId}/{bookId}/rating", async (
    int userId, int bookId, UpdateRatingDTO request, IMongoDatabase database) =>
    {
        if (request.Rating < 1 || request.Rating > 5)
            return Results.BadRequest("A avaliação deve estar entre 1 e 5.");

        var collection =
            database.GetCollection<UserLibrary>("userLibraries");

        var libraryBook = await collection
            .Find(x =>
                x.UserId == userId &&
                x.BookId == bookId)
            .FirstOrDefaultAsync();

        if (libraryBook is null)
            return Results.NotFound("Livro não encontrado na biblioteca.");

        libraryBook.Rating = request.Rating;

        await collection.ReplaceOneAsync(
            x =>
                x.UserId == userId &&
                x.BookId == bookId,
            libraryBook);

        return Results.Ok(libraryBook);
    }).RequireAuthorization();;

app.MapPatch("/api/library/{userId}/{bookId}/tags", async (
    int userId, int bookId, UpdateTagsDTO request, IMongoDatabase database) =>
    {
        var collection =
            database.GetCollection<UserLibrary>("userLibraries");

        var libraryBook = await collection
            .Find(x =>
                x.UserId == userId &&
                x.BookId == bookId)
            .FirstOrDefaultAsync();

        if (libraryBook is null)
        {
            return Results.NotFound(
                "Livro não encontrado na biblioteca.");
        }

        libraryBook.Tags = request.Tags;

        await collection.ReplaceOneAsync(
            x =>
                x.UserId == userId &&
                x.BookId == bookId,
            libraryBook);

        return Results.Ok(libraryBook);
    }).RequireAuthorization();;

app.MapDelete("/api/library/{userId}/{bookId}", async (
    int userId, int bookId, IMongoDatabase database) =>
    {
        var collection =
            database.GetCollection<UserLibrary>("userLibraries");

        var result = await collection.DeleteOneAsync(x =>
            x.UserId == userId &&
            x.BookId == bookId);

        if (result.DeletedCount == 0)
            return Results.NotFound("Livro não encontrado na biblioteca.");

        return Results.Ok("Livro removido da biblioteca.");
    }).RequireAuthorization();;

#endregion

#region Home

app.MapGet("/", () => "Hello World!");
#endregion

#region Livro

app.MapPost("/books", async (LivroCreateDTO livroCreateDTO, AppDbContext db) =>
{
    // Validando e Criando o autor
    var nomeAutor = livroCreateDTO.Autor.Trim();

    var autor = await db.Autores
        .FirstOrDefaultAsync(a => a.Nome == nomeAutor);

    if (autor == null)
    {
        autor = new Autor { Nome = livroCreateDTO.Autor };
        db.Autores.Add(autor);
        await db.SaveChangesAsync();
    }

    //Criando o livro

    var livro = new Livro
    {
        Titulo = livroCreateDTO.Titulo,
        Sinopse = livroCreateDTO.Sinopse,
        Total_Paginas = livroCreateDTO.Total_Paginas,
        Id_Autor = autor.Id_Autor,

    };

    db.Livros.Add(livro);
    await db.SaveChangesAsync();

    return Results.Created($"/books/{livro.Id_Livro}", livro);

}).RequireAuthorization();;

app.MapGet("/books", async (AppDbContext db) =>
{
      var livros = await db.Livros
        .Include(l => l.Autor)
        .ToListAsync();

    var response = livros.Select(l => new BibliotecaLivroResponseDTO
    {
        Titulo = l.Titulo,
        Autor = l.Autor.Nome,
    });

    return Results.Ok(response);
}).RequireAuthorization();;

#endregion

#region DaylyStat

app.MapPost("/stats", async (
    DailyStatCreateDTO dto, AppDbContext context) =>
    {
        var usuarioExiste = await context.Usuarios
            .AnyAsync(x => x.Id_Usuario == dto.UserId);

        if (!usuarioExiste)
        {
            return Results.NotFound("Usuário não encontrado.");
        }

        var stat = new DailyStat
        {
            Id_Usuario = dto.UserId,
            Data_Leitura = dto.DataLeitura,
            Total_Paginas_Lidas = dto.TotalPaginasLidas,
            Tempo_Leitura = dto.TempoLeitura
        };

        context.DailyStats.Add(stat);

        await context.SaveChangesAsync();

        return Results.Created($"/stats/{dto.UserId}", stat);
    }).RequireAuthorization();;

app.MapGet("/stats/{userId}", async (
    int userId, AppDbContext context) =>
    {
        var stats = await context.DailyStats
            .Where(x => x.Id_Usuario == userId)
            .OrderByDescending(x => x.Data_Leitura)
            .ToListAsync();

        return Results.Ok(stats);
    }).RequireAuthorization();;

#endregion

#endregion

app.UseAuthentication();
app.UseAuthorization();

app.UseSwagger();
app.UseSwaggerUI();

app.Run();
