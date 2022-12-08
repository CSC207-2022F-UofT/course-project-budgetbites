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

    public HashMap<String, Order> getPastOrdersMap () {
        return this.pastOrdersMap;
    }

    public String getLastOrdered () {
        return this.lastOrdered;
    }

    public Order getOrderByDate (String dateTime) {
        return this.pastOrdersMap.get(dateTime);
    }

    public ArrayList<FoodItem> getOrderedItemsByDate (String dateTime) {
        return this.pastOrdersMap.get(dateTime).getOrderedItems();
    }

    public double getTotalCost () {
        double cost = 0;
        for (Order order: this.pastOrdersMap.values()){
            cost += order.getTotalCost();
        }

        return cost;
    }

    public double getCostOfLastOrdered () {
        if (this.getPastOrdersMap().isEmpty()){
            return 0;
        }
        return this.getOrderByDate(this.lastOrdered).getTotalCost();
    }

    public void addOrder (Order order) {
        this.pastOrdersMap.put(order.getDateOrdered(), order);
        this.lastOrdered = order.getDateOrdered();
    }
}
