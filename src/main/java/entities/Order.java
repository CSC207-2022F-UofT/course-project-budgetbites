package entities;

import java.util.ArrayList;

public class Order {
    public ArrayList<FoodItem> orderedItems;
    public String dateOrdered;
    public double totalCost;
    public String restaurantName;


    public Order () {
        this.dateOrdered = null;
        this.totalCost = 0;
        this.orderedItems = new ArrayList<>();
        this.restaurantName = "";
    }

    public Order (String dateOrdered, String restaurantName) {
        this.dateOrdered = dateOrdered;
        this.totalCost = 0;
        this.orderedItems = new ArrayList<>();
        this.restaurantName = restaurantName;
    }

    public ArrayList<FoodItem> getOrderedItems () {
        return this.orderedItems;
    }

    public double getTotalCost () {
        return this.totalCost;
    }

    public String getDateOrdered () {
        return this.dateOrdered;
    }
    public String getRestaurantName () {return this.restaurantName;}


    public void addToOrder (FoodItem item) {
        this.orderedItems.add(item);
        this.totalCost += item.getItemCost();
        }



}
