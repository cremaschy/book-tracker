using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.DTOs
{
    public class BibliotecaLivroResponseDTO
    {
        public int LivroId { get; set; }

        public string Titulo { get; set; } = string.Empty;

        public string Autor { get; set; } = string.Empty;

        public int TotalPaginas { get; set; }

        public string Status { get; set; } = string.Empty;

        public int CurrentPage { get; set; }
    }
}