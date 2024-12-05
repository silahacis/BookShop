namespace BookStoreAPI.Payments
{
    public interface IPaymentStrategy
    {
        string Pay(double amount);
    }
}
