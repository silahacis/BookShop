namespace BookStoreAPI.Interfaces
{
    public interface IObservable
    {
        void Register(IObserver observer);
        void Unregister(IObserver observer);
        void NotifyObservers(string orderStatusMessage);
    }
}
