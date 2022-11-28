package entities;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public String restaurantName;
    public String priceRange;
    public String cuisine;
    public String foodType;
    public double avgRating;
    public ArrayList<FoodItem> menu;
    public List<Object[]> reviews;

    public Restaurant () {

    }

    public Restaurant (String name, String priceRange, String cuisine, String foodType, double avgRating, ArrayList<FoodItem> menu) {
        this.restaurantName = name;
        this.priceRange = priceRange;
        this.cuisine = cuisine;
        this.foodType = foodType;
        this.avgRating = avgRating;
        this.menu = menu;
    }


    public String getPriceRange(){
        return this.priceRange;
    }

    public String getCuisine() {
        return this.cuisine;
    }

    public String getFoodType() {
        return this.foodType;
    }

    public String getRestaurantName () {return this.restaurantName;}

    public double getAvgRating () {
        return this.avgRating;
    }

    public ArrayList<FoodItem> getMenu () {
        return this.menu;
    }

    public List<Object[]> getReviews () {
        return this.reviews;
    }


}
