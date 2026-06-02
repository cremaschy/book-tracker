using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookTracker.Api.DTOs
{
    public class AddBookToLibraryRequest
    {
        public int UserId { get; set; }
        public int BookId { get; set; }
    }
}