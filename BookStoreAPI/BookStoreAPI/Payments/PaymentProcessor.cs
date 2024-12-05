namespace BookStoreAPI.Payments
{
    public class PaymentProcessor
    {
        public IPaymentStrategy PaymentStrategy { get; private set; }
        public PaymentProcessor(IPaymentStrategy paymentStrategy) 
        { 
            this.PaymentStrategy = paymentStrategy; 
        }

        public PaymentProcessor(string paymentMethod)
        {
            SetPaymentMethod(paymentMethod);
        }

        public void SetPaymentMethod(string paymentMethod)
        {
            PaymentStrategy = paymentMethod switch
            {
                "CreditCard" => new CreditCardPayment(),
                "PayPal" => new PayPalPayment(),
                _ => throw new Exception("Invalid or unsupported payment method")
            };
        }

        public string ProcessPayment(double amount)
        {
            return PaymentStrategy.Pay(amount);
        }
    }
}
