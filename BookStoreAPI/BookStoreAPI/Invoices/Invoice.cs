namespace BookStoreAPI.Invoices
{
    public abstract class Invoice
    {
        public int Id { get; set; }
        public string InvoiceText { get; protected set; }
        public DateTime InvoiceDate { get; set; }

        public abstract void GenerateInvoice(double amount);
    }
}
