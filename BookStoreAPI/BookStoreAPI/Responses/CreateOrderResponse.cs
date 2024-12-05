using BookStoreAPI.Entities;

namespace BookStoreAPI.Responses
{
    public class CreateOrderResponse
    {
        public Order Order { get; set; }
        public string PaymentMethodMessage { get; set; }
        public DateTime InvoiceDate { get; set; }
        public string InvoiceText { get; set; }
        public List<string> OrderMessages { get; set; }
    }

}
