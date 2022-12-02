package usecases;

import entities.Restaurant;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public interface FilterManagerDAI {
    public ArrayList<String> getAllRestaurants ();

    public String getRestaurantAttribute (String restaurantName, String attribute);

}
