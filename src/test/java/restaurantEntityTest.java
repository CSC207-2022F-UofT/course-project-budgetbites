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

    @Before
    public void init () {
        FoodItem f1 = new FoodItem("Chicken Shawarma", 8);
        FoodItem f2 = new FoodItem("Hummus with Pita", 5);
        FoodItem f3 = new FoodItem("Falafel", 4);
        ArrayList<FoodItem> menu = new ArrayList<>(Arrays.asList(f1, f2, f3));

        restaurant = new Restaurant("Food from East", "Intermediate",
                "Middle-East", "Lunch", 5, menu);
    }

    @Test
    public void getPriceRangeTest() {
        String price = restaurant.getPriceRange();

        Assertions.assertEquals("Intermediate", price);
    }

    @Test
    public void getCuisineTest() {
        String cuisine = restaurant.getCuisine();

        Assertions.assertEquals("Middle-East", cuisine);
    }

    @Test
    public void getFoodType() {

        String foodType = restaurant.getFoodType();

        Assertions.assertEquals("Lunch", foodType);
    }

    @Test
    public void getRestaurantNameTest() {
        String name = restaurant.getRestaurantName();

        Assertions.assertEquals("Food from East", name);
    }

    @Test
    public void getAvgRatingTest() {

        double rating = restaurant.getAvgRating();

        Assertions.assertEquals(5, rating);
    }
}
