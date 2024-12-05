namespace BookStoreAPI.Entities
{
    public class OrderSimulation
    {
        public OrderSimulation() {}
        public void SimulateOrderProcess(Order order)
        {
            order.ProcessOrder();
           

            Thread.Sleep(2000); 
            order.ShipOrder();
            order.NotifyObservers();

            Thread.Sleep(2000);
            order.DeliverOrder();
            order.NotifyObservers();
        }
    }
}
