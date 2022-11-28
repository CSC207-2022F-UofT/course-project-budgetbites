package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class PastOrders {
    public HashMap<String, Order> pastOrdersMap;
    public String lastOrdered;

    public PastOrders() {
        this.pastOrdersMap = new HashMap<String, Order>();
        this.lastOrdered = null;
    }

    public PastOrders(HashMap<String, Order> pastOrdersMap, String lastOrdered) {
        this.pastOrdersMap = pastOrdersMap;
        this.lastOrdered = lastOrdered;
    }

    public ArrayList<FoodItem> getOrderedItemsByDate (String dateTime) {
        return this.pastOrdersMap.get(dateTime).getOrderedItems();
    }

    public Order getOrderByDate (String dateTime) {
        return this.pastOrdersMap.get(dateTime);
    }

    public String getLastOrdered () {
        return this.lastOrdered;
    }

    public HashMap<String, Order> getPastOrdersMap () {
        return this.pastOrdersMap;
    }

    public void addOrder (Order order) {
        this.pastOrdersMap.put(order.dateOrdered, order);
        this.lastOrdered = order.dateOrdered;
    }

    public double getTotalCost () {
        double cost = 0;
        for (Order order: this.pastOrdersMap.values()){
            cost += order.getTotalCost();
        }

        return cost;
    }

    public double getCostOfLastOrdered () {
        return this.getOrderByDate(this.lastOrdered).getTotalCost();
    }
}
