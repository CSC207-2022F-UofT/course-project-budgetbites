package usecases;

import entities.Order;
import entities.PastOrders;

public interface MakeOrderDAI {
    public PastOrders findPastOrders (String username);
    public boolean addToPastOrders (String username, Order order);
}