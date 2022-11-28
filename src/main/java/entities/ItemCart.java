package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ItemCart {
    public ArrayList<FoodItem> currentOrder;

    public ItemCart() {
        this.currentOrder = new ArrayList<FoodItem>();
    }

    public void addToCart (FoodItem item) {
        currentOrder.add(item);
    }

    public Order makeOrder (PastOrders userPastOrders, String restaurantName) {
        String dateTime = LocalDateTime.now().toString();
        Order tempOrder = new Order(dateTime, restaurantName);
//        Get the current date and time of when the order is being placed
//        Create the order with the dateTime and restaurant

        for (FoodItem item: this.currentOrder) {
            item.addToOrder(tempOrder);
        }

        this.currentOrder.clear();

        userPastOrders.addOrder(tempOrder);
        return tempOrder;
    }

}
