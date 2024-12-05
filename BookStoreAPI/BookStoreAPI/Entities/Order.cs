using BookStoreAPI.Enums;
using BookStoreAPI.Interfaces;
using BookStoreAPI.Invoices;
using BookStoreAPI.Payments;

namespace BookStoreAPI.Entities
{
    public class Order
    {
        //Atributes
        public int Id { get; set; }
        public ICollection<OrderItem> OrderItems { get; set; }
        public double TotalAmount { get; set; }
        public Customer Customer { get; set; }
        public virtual IOrderState State { get; set; }
        public DateTime OrderDate { get; set; }
        public virtual Invoice Invoice { get; set; }
        public virtual PaymentStrategy PaymentStrategy { get; set; }

        
        public static Order CreateOrder(
                ICollection<BaseProduct> products,
                double totalAmount,
                Customer customer,
                InvoiceType invoiceType,  // Enum türünü kullanıyoruz
                PaymentStrategy paymentStrategy
                )
        {
            // Factory kullanarak doğru faturayı oluştur
            IInvoiceFactory invoiceFactory = new ConcreteInvoiceFactory();
            Invoice invoice = invoiceFactory.CreateInvoice(invoiceType);  // Enum'u string'e çeviriyoruz

            // Faturayı oluştur
              // OrderId ve tutarı kullanarak faturayı oluşturuyoruz

            // Yeni bir sipariş oluştur ve invoice text'i dahil et
            return new Order
            {
                BaseProduct = products,
                TotalAmount = totalAmount,
                Customer = customer,
                OrderDate = DateTime.Now,
                Invoice = invoice,
                PaymentStrategy = paymentStrategy,
                InvoiceText = invoice.InvoiceText,  // Invoice metnini Order'a ekliyoruz
            };
        }

    }
}
