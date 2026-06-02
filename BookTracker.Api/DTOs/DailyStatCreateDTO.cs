using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.DTOs
{
    public class DailyStatCreateDTO
    {
        public int UserId { get; set; }
        public DateTime DataLeitura { get; set; }
        public int TotalPaginasLidas { get; set; }
        public TimeSpan? TempoLeitura { get; set; }
    }
}