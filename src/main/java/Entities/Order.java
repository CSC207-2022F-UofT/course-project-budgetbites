package Entities;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    public ArrayList<foodItem> orderedItems;
    public LocalDateTime dateOrdered;
    public double totalCost;

    public Order (ArrayList<foodItem> orderedItems, LocalDateTime dateOrdered) {
        this.orderedItems = orderedItems;
        this.dateOrdered = dateOrdered;
        this.totalCost = 0;

        for (foodItem item: orderedItems) {
            totalCost += item.itemCost;
        }

    }

    public Order (LocalDateTime dateOrdered) {
        this.orderedItems = new ArrayList<foodItem>();
        this.dateOrdered = dateOrdered;
        this.totalCost = 0;
    }

    public ArrayList<foodItem> getOrderedItems () {
        return this.orderedItems;
    }


    public void addToOrder (foodItem item) {
        this.orderedItems.add(item);
        this.totalCost += item.getItemCost();
        }

}
