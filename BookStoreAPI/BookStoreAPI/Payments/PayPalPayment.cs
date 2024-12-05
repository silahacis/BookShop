namespace BookStoreAPI.Payments
{
    public class PayPalPayment : IPaymentStrategy
    {
        public string Pay(double amount)
        {
            string message = $"Paid {amount:C} using PayPal.";
            Console.WriteLine(message);
            return message;
        }
    }
}
