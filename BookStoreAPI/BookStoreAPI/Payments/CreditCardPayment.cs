namespace BookStoreAPI.Payments
{
    public class CreditCardPayment : IPaymentStrategy
    {
        public string Pay(double amount)
        {
            string message = $"Paid {amount:C} using Credit Card.";
            return message;
        }
    }
}
