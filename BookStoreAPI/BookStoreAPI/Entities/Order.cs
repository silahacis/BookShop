using BookStoreAPI.Interfaces;
using BookStoreAPI.Invoices;
using BookStoreAPI.Payments;

namespace BookStoreAPI.Entities
{
    public class Order 
    {
        //Atributes
        public int Id { get; set; }
        public List<BaseProduct> Products { get; set; }
        public double TotalAmount { get; set; }
        public Customer Customer { get; set; }
        public  IOrderState State { get; set; }
        public DateTime OrderDate { get; set; }
        public  Invoice Invoice { get; set; }
        public  IPaymentStrategy PaymentStrategy { get; set; }
    }
}
