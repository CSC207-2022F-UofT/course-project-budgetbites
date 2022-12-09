package entities.designpatterns;

import entities.FoodItem;
import entities.Restaurant;

import java.util.ArrayList;

public class RestaurantFactory {
    /**
     * Creates and returns a new restaurant with all the given parameters
     * @param restaurantName: name of the restaurant
     * @param priceRange: price range of the restaurant
     * @param cuisine: cuisine of the restaurant
     * @param foodType: food type of the restaurant
     * @param avgRating: average rating of the restaurant
     * @param menu: the menu of the restaurant
     */
    public static Restaurant getRestaurant(String restaurantName, String priceRange, String cuisine,
                                           String foodType, double avgRating, ArrayList<FoodItem> menu) {
        return new Restaurant(restaurantName, priceRange, cuisine, foodType, avgRating, menu);
    }
}