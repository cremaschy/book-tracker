using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.DTOs
{
    public class UpdateTagsDTO
    {
        public List<string> Tags { get; set; } = [];
    }
}