package entityTesting;

import entities.FoodItem;
import entities.Restaurant;
import org.junit.Before;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class restaurantEntityTest {

    private Restaurant restaurant;
    private ArrayList<FoodItem> menu;

    /**
     * initializes all instance attributes with values
     */
    @Before
    public void init () {
        FoodItem f1 = new FoodItem("Chicken Shawarma", 8);
        FoodItem f2 = new FoodItem("Hummus with Pita", 5);
        FoodItem f3 = new FoodItem("Falafel", 4);
        menu = new ArrayList<>(Arrays.asList(f1, f2, f3));

        restaurant = new Restaurant("Food from East", "Intermediate",
                "Middle-East", "Lunch", 5, menu);
    }

    /**
     * Tests if correct price range is outputted from a placed order
     */
    @Test
    public void getPriceRangeTest() {
        String price = restaurant.getPriceRange();

        Assertions.assertEquals("Intermediate", price);
    }

    /**
     * Tests if correct cuisine is outputted from a placed order
     */
    @Test
    public void getCuisineTest() {
        String cuisine = restaurant.getCuisine();

        Assertions.assertEquals("Middle-East", cuisine);
    }

    /**
     * Tests if correct food type is outputted from a placed order
     */
    @Test
    public void getFoodType() {

        String foodType = restaurant.getFoodType();

        Assertions.assertEquals("Lunch", foodType);
    }

    /**
     * Tests if correct restaurant name is outputted from a placed order
     */
    @Test
    public void getRestaurantNameTest() {
        String name = restaurant.getRestaurantName();

        Assertions.assertEquals("Food from East", name);
    }

    /**
     * Tests if correct avaerage rating is outputted from a placed order
     */
    @Test
    public void getAvgRatingTest() {

        double rating = restaurant.getAvgRating();

        Assertions.assertEquals(5, rating);
    }

    /**
     * Tests if correct menu is outputted from a placed order
     */
    @Test
    public void getMenuTest() {
        Assertions.assertEquals(restaurant.getMenu(), menu);
    }
}
