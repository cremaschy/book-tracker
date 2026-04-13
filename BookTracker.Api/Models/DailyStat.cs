using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.Models
{
    public class DailyStat
    {
        public int Id_Ds { get; set; }
        public DateTime Data_Leitura { get; set; }
        public int Total_PaginasLidas { get; set; }
        public TimeSpan? Tempo_Leitura { get; set; }
        public int Id_Livro { get; set; }
        public int Id_Usuario { get; set; }
    }
}