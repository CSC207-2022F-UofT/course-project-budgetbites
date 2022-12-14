package entityTesting;

import entities.FoodItem;
import entities.Restaurant;
import entities.designpatterns.RestaurantFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
@SuppressWarnings({"ALL"})
public class RestaurantFactoryTest {
    private final String restaurantName = "Food from East";
    private final String cuisine = "Middle-East";
    private final String priceRange = "Cheap";
    private final String foodType = "Lunch";
    private final double avgRating = 5.0;
    private final ArrayList<FoodItem> menu = new ArrayList<>(Arrays.asList(
            new FoodItem("Chicken Shawarma", 8), new FoodItem("Falafel", 4),
            new FoodItem("Hummus with Pita", 5)));


    /**
     * Tests if restaurant with accurate name, price range, cuisine, food type, average rating and menu is provided
     */
    @Test
    public void getRestaurantTest() {
        Restaurant restaurant = RestaurantFactory.getRestaurant(restaurantName, priceRange,
                cuisine, foodType, avgRating, menu);

        Assertions.assertEquals(restaurant.getRestaurantName(), restaurantName);
        Assertions.assertEquals(restaurant.getPriceRange(), priceRange);
        Assertions.assertEquals(restaurant.getCuisine(), cuisine);
        Assertions.assertEquals(restaurant.getFoodType(), foodType);
        Assertions.assertEquals(restaurant.getAvgRating(), avgRating);
        Assertions.assertEquals(restaurant.getMenu(), menu);

    }
}
