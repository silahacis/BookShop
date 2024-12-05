namespace BookStoreAPI.Invoices
{
    public class PhysicalInvoice : Invoice
    {
        public override void GenerateInvoice(double amount)
        {
            InvoiceText = $"Generated a physical invoice for the order. Amount: ${amount}";
        }
    }

}
