using BookStoreAPI.Entities;

namespace BookStoreAPI.Interfaces
{
    public interface IObserver
    {
        void Update(Order order, string orderStatusMessage);
    }
}
