namespace BookStoreAPI.Invoices
{
    public class DigitalInvoice : Invoice
    {
        public override void GenerateInvoice(double amount)
        {
            InvoiceText = $"Generated a digital invoice (PDF) for the order. Amount: ${amount}";
            InvoiceDate = DateTime.Now;
        }
    }

}
