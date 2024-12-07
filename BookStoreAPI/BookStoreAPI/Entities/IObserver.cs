namespace BookStoreAPI.Entities
{
    public interface IObserver
    {
        void Update(Order order, string orderStatusMessage);
    }
}
