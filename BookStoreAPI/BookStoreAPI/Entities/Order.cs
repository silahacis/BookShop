using BookStoreAPI.Interfaces;
using BookStoreAPI.Invoices;
using BookStoreAPI.Payments;

namespace BookStoreAPI.Entities
{
    public class Order
    {
        //Atributes
        public int Id { get; set; }
        public List<OrderItem> OrderItems { get; set; }
        public double TotalAmount { get; set; }
        public Customer Customer { get; set; }
        public virtual IOrderState State { get; set; }
        public DateTime OrderDate { get; set; }
        public virtual Invoice Invoice { get; set; }
        public virtual PaymentStrategy PaymentStrategy { get; set; }
    }
}
