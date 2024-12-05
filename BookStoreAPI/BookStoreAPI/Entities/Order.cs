using BookStoreAPI.Enums;
using BookStoreAPI.Interfaces;
using BookStoreAPI.Invoices;
using BookStoreAPI.OrderStates;
using BookStoreAPI.Payments;
using BookStoreAPI.Responses;

namespace BookStoreAPI.Entities;
public class Order : IObservable
{
    //Atributes
    public int Id { get; set; }
    public List<Book> Books { get; set; }
    public double TotalAmount { get; set; }
    public Customer Customer { get; set; }
    public DateTime OrderDate { get; set; }
    public string PaymentMessage { get; set; }
    public List<string> OrderMessages { get; set; } = new List<string>();
    public string OrderStatus { get; set; }
    public IOrderState State { get; set; }
    public virtual Invoice Invoice { get; set; }
    public virtual IPaymentStrategy PaymentStrategy { get; set; }

    private readonly List<IObserver> Observers = new();

    public static CreateOrderResponse CreateOrder(List<Book> books, Customer customer, PaymentProcessor paymentProcessor, IInvoiceFactory invoiceFactory, InvoiceType invoiceType)
    {
        double totalAmount = books.Sum(book => book.Price);

        var order = new Order
        {
            Id = GenerateOrderId(),
            Books = books,
            Customer = customer,
            TotalAmount = totalAmount,
            OrderDate = DateTime.Now,
            State = PendingState.Create(),
            PaymentStrategy = paymentProcessor.PaymentStrategy,
        };

        Invoice invoice = invoiceFactory.CreateInvoice(invoiceType);
        invoice.GenerateInvoice(totalAmount);

        order.Invoice = invoice;

        string paymentMessage = paymentProcessor.ProcessPayment(totalAmount);

        return new CreateOrderResponse
        {
            Order = order,
            PaymentMethodMessage = paymentMessage,
            InvoiceDate = invoice.InvoiceDate,
            InvoiceText = invoice.InvoiceText,
        };
    }
    public void Register(IObserver observer)
    {
        Observers.Add(observer);
    }

    public void Unregister(IObserver observer)
    {
        Observers.Remove(observer);
    }

    public void NotifyObservers(string orderStatusMessage)
    {
        foreach (var observer in Observers)
        {
            observer.Update(this);
        }
    }

    public void ProcessOrder()
    {
        State.ProcessOrder(this);
    }

    public void ShipOrder()
    {
        State.ShipOrder(this);
    }

    public void DeliverOrder()
    {
        State.DeliverOrder(this);
    }
    private static int GenerateOrderId()
    {
        return new Random().Next(1000, 9999);
    }
}
