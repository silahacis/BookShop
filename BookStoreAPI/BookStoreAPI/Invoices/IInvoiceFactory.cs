using BookStoreAPI.Enums;

namespace BookStoreAPI.Invoices
{
    public interface IInvoiceFactory
    {
        Invoice CreateInvoice(InvoiceType type);
    }

}
