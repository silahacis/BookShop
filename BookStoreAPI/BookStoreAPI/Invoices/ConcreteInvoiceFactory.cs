using BookStoreAPI.Enums;
using BookStoreAPI.Invoices;

public class ConcreteInvoiceFactory : IInvoiceFactory
{
    public Invoice CreateInvoice(InvoiceType type)
    {
        return type switch
        {
            InvoiceType.Physical => new PhysicalInvoice(),
            InvoiceType.Digital => new DigitalInvoice(),
            _ => throw new ArgumentException("Unknown invoice type")
        };
    }
}
