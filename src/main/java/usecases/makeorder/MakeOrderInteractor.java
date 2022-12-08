package usecases.makeorder;

import entities.FoodItem;
import entities.ItemCart;
import entities.Order;
import entities.PastOrders;
import gateways.MainMongoDB;
import usecases.MakeOrderDAI;


import java.time.LocalDateTime;

public class MakeOrderInteractor {
    private final MakeOrderDAI makeOrderDAI = new MainMongoDB();
    public Order makeNewOrder (String restaurantName, ItemCart itemCart, String username) {
        PastOrders pastOrders = makeOrderDAI.findPastOrders(username);
        Order order = itemCart.makeOrder(pastOrders, restaurantName);
        makeOrderDAI.addToPastOrders(username, order);
        return order;
    }
}