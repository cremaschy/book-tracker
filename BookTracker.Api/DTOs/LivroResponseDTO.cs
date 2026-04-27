using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.DTOs
{
    public class LivroResponseDTO
    {
        public string Titulo { get; set; }
        public string Autor { get; set; }
        public string Situacao { get; set; }
    }
}