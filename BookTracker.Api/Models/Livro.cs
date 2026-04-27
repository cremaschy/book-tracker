using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.Models
{
    public class Livro
    {
        public int Id_Livro { get; set; }
        public string Titulo { get; set; }
        public string Sinopse { get; set; }
        public int Total_Paginas { get; set; }  
        public int? Pagina_Atual { get; set; }

        public int Id_Autor { get; set; }
        public Autor Autor { get; set; }
        public int Id_Situacao { get; set; }
        public Situacao Situacao { get; set; }
    }
}