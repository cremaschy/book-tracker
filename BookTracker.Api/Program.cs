using BookTracker.Api.Data;
using BookTracker.Api.DTOs;
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

    // Validando a Situação 

     var situacaoExiste = await db.Situacoes
        .AnyAsync(s => s.Id_Situacao == livroCreateDTO.Id_Situacao);

    if (!situacaoExiste)
        return Results.BadRequest("Situação inválida");

    //Criando o livro

    var livro = new Livro
    {
        Titulo = livroCreateDTO.Titulo,
        Sinopse = livroCreateDTO.Sinopse,
        Total_Paginas = livroCreateDTO.Total_Paginas,
        Id_Autor = autor.Id_Autor,
        Id_Situacao = livroCreateDTO.Id_Situacao
    };

    db.Livros.Add(livro);
    await db.SaveChangesAsync();

    return Results.Created($"/books/{livro.Id_Livro}", livro);

});

app.MapGet("/books", async (AppDbContext db) =>
{
      var livros = await db.Livros
        .Include(l => l.Autor)
        .Include(l => l.Situacao)
        .ToListAsync();

    var response = livros.Select(l => new LivroResponseDTO
    {
        Titulo = l.Titulo,
        Autor = l.Autor.Nome,
        Situacao = l.Situacao.Descricao
    });

    return Results.Ok(response);
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

#region Situação
app.MapGet("/situacoes", async (AppDbContext db) =>
{
    var situacoes = await db.Situacoes.ToListAsync();
    return Results.Ok(situacoes);
});

#endregion

app.UseSwagger();
app.UseSwaggerUI();

app.Run();
