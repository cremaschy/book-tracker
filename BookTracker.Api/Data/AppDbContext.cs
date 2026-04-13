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
        public DbSet<Livro> Livros { get; set; }
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

            // Livro
            modelBuilder.Entity<Livro>(entity =>
            {
                entity.ToTable("livros");
                entity.HasKey(e => e.Id_Livro);
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