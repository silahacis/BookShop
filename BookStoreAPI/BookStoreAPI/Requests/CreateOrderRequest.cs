using BookStoreAPI.Entities;
using BookStoreAPI.Enums;

namespace BookStoreAPI.Requests
{
    public class CreateOrderRequest
    {
        public List<Book> Books { get; set; }
        public int CustomerId { get; set; }
        public string PaymentMethod { get; set; }
        public string OrderAddress { get; set; }
        public InvoiceType InvoiceType { get; set; }
    }
}
