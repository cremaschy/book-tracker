using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BookTracker.Api.Models;
using Microsoft.EntityFrameworkCore;

namespace BookTracker.Api.Data
{
    public class AppDbContext : DbContext
    {
        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Autor> Autores { get; set; }
        public DbSet<Livro> Livros { get; set; }
        public DbSet<Situacao> Situacoes { get; set; }
        public DbSet<DailyStat> DailyStats { get; set; }

        public AppDbContext(DbContextOptions<AppDbContext> options)
            : base(options) { }
        
       protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Usuario
            modelBuilder.Entity<Usuario>(entity =>
            {
                entity.ToTable("usuarios");
                entity.HasKey(e => e.Id_Usuario);
            });

            // Autor
            modelBuilder.Entity<Autor>(entity =>
            {
                entity.ToTable("autores");
                entity.HasKey(e => e.Id_Autor);
            });

            // Livro
            modelBuilder.Entity<Livro>(entity =>
            {
                entity.ToTable("livros");
                entity.HasKey(e => e.Id_Livro);
            });

            modelBuilder.Entity<Livro>()
                .HasOne(l => l.Autor)
                .WithMany()
                .HasForeignKey(l => l.Id_Autor);

            modelBuilder.Entity<Livro>()
                .HasOne(l => l.Situacao)
                .WithMany()
                .HasForeignKey(l => l.Id_Situacao);

            // modelBuilder.Entity<Livro>()
            // .Property(l => l.Pagina_Atual)
            // .HasColumnName("pagina_atual");

            // Situação
            modelBuilder.Entity<Situacao>(entity =>
            {
                entity.ToTable("situacoes");
                entity.HasKey(e => e.Id_Situacao);
            });

            // DailyStat
            modelBuilder.Entity<DailyStat>(entity =>
            {
                entity.ToTable("DailyStats");
                entity.HasKey(e => e.Id_Ds);
            });
        }
    }
}