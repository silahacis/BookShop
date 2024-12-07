using BookStoreAPI.Entities;
using BookStoreAPI.Interfaces;
using BookStoreAPI.Invoices;
using BookStoreAPI.Payments;

namespace BookStoreAPI.Responses
{
    public class GetOrderResponse
    {
        public int Id { get; set; }
        public List<Book> Books { get; set; }
        public double TotalAmount { get; set; }
        public string OrderAddress { get; set; }
        public DateTime OrderDate { get; set; }
        public List<string> OrderMessages { get; set; } = new List<string>();
        public string InvoiceType { get; set; }
        public string PaymentMethod { get; set; }
    }
}
