package Entities;

import java.util.List;

public class Restaurant {
    public String priceRange;
    public String cuisine;
    public String foodType;
    public double avgRating;
    public foodItem[] menu;
    public List<Object[]> reviews;

    public Restaurant (String priceRange, String cuisine, String foodType, double avgRating, foodItem[] menu) {
        this.priceRange = priceRange;
        this.cuisine = cuisine;
        this.foodType = foodType;
        this.avgRating = avgRating;
        this.menu = menu;
    }
}
