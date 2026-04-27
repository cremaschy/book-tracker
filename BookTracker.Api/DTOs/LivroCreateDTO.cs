using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.DTOs
{
    public class LivroCreateDTO
    {
        public string Titulo { get; set; }
        public string Sinopse { get; set; }
        public int Total_Paginas { get; set; }
        public string Autor { get; set; }
        public int Id_Situacao { get; set; }
    }
}