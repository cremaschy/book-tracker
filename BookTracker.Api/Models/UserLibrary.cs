using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BookTracker.Api.Models
{
    public class UserLibrary
    {
        [BsonId]
        public ObjectId Id { get; set; }
        public int UserId { get; set; }
        public int BookId { get; set; }
        public string Status { get; set; }
        public int CurrentPage { get; set; }
        public int? Rating { get; set; }
        public List<string> Tags { get; set; }
        public DateTime? StartedAt { get; set; }
        public DateTime? FinishedAt { get; set; }

        // public List<Note> Notes { get; set; }
    }
}