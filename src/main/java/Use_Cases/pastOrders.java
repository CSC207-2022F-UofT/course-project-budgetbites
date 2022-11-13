package Use_Cases;

import Entities.Order;
import Entities.foodItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class pastOrders {
    public HashMap<LocalDateTime, Order> pastOrders;
    public LocalDateTime lastOrdered;

    public pastOrders () {
        this.pastOrders = new HashMap<LocalDateTime, Order>();
        this.lastOrdered = null;
    }

    public ArrayList<foodItem> getOrderByDate (LocalDateTime dateTime) {
        return this.pastOrders.get(dateTime).getOrderedItems();
    }

    public LocalDateTime getLastOrdered () {
        return this.lastOrdered;
    }

    public void addOrder (Order order) {
        this.pastOrders.put(order.dateOrdered, order);
        this.lastOrdered = order.dateOrdered;
    }
}
