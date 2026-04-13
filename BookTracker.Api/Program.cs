using BookTracker.Api.Data;
using BookTracker.Api.Models;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);


var connectionString = builder.Configuration.GetConnectionString("Default");

builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseMySql(connectionString,
    ServerVersion.AutoDetect(connectionString)));

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build(); 

app.MapGet("/test-db", async (AppDbContext db) =>
{
    var usuarios = await db.Usuarios.ToListAsync();
    return Results.Ok(usuarios);
});

#region Usuario 

app.MapPost("/login", async (AppDbContext db, Usuario login) =>
{
    var usuario = await db.Usuarios
        .FirstOrDefaultAsync(u => u.Email == login.Email && u.Senha == login.Senha);

    if (usuario == null)
        return Results.Unauthorized();

    return Results.Ok(usuario);
});

app.MapGet("/usuarios/nome/{nome}", async (string nome, AppDbContext db) =>
{
    var usuarios = await db.Usuarios
        .Where(u => u.Nome.Contains(nome))
        .ToListAsync();

    return Results.Ok(usuarios);
});

#endregion

#region Home

app.MapGet("/", () => "Hello World!");
#endregion

#region Livro

app.MapPost("/books", async (Livro livro, AppDbContext db) =>
{
    db.Livros.Add(livro);
    await db.SaveChangesAsync();
    return Results.Created($"/books/{livro.Id_Livro}", livro);
});

app.MapGet("/books", async (AppDbContext db) =>
{
    return await db.Livros.ToListAsync();
});

#endregion

#region DaylyStat

app.MapPost("/Leitura", async (DailyStat stat, AppDbContext db) =>
{
    db.DailyStats.Add(stat);
    await db.SaveChangesAsync();
    return Results.Ok(stat);
});
#endregion

app.UseSwagger();
app.UseSwaggerUI();

app.Run();
