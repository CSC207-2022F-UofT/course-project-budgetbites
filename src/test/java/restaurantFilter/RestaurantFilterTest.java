package restaurantFilter;

import usecases.filtering.restaurantfiltering.RestaurantFilter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RestaurantFilterTest {
    /**
     * Creates ArrayList with cuisine, Food Type, and expense
     */
    private ArrayList<String> allRestaurants = new ArrayList<>(Arrays.asList(
            "Italian Breakfast Cheap", "Chinese Lunch Cheap", "Thai Dinner Expensive", "French Lunch Intermediate",
            "Arabic Snack Cheap", "Indian Dinner Intermediate", "Middle-East Lunch Expensive", "Chinese Snack Expensive",
            "Mexican Breakfast Intermediate"));

    public static RestaurantFilter generateFilterManager() {
        HashMap<String, ArrayList<String>> cuisineFilter = new HashMap<>();
        cuisineFilter.put("Italian",new ArrayList<String>());
        cuisineFilter.put("Chinese",new ArrayList<String>());
        cuisineFilter.put("Thai",new ArrayList<String>());
        cuisineFilter.put("French",new ArrayList<String>());
        cuisineFilter.put("Arabic",new ArrayList<String>());
        cuisineFilter.put("Mexican",new ArrayList<String>());
        cuisineFilter.put("Indian",new ArrayList<String>());
        cuisineFilter.put("Middle-East",new ArrayList<String>());

        HashMap<String, ArrayList<String>> foodTypeFilter = new HashMap<>();
        foodTypeFilter.put("Breakfast",new ArrayList<String>());
        foodTypeFilter.put("Lunch",new ArrayList<String>());
        foodTypeFilter.put("Dinner",new ArrayList<String>());
        foodTypeFilter.put("Snack",new ArrayList<String>());

        HashMap<String, ArrayList<String>> priceFilter = new HashMap<>();
        priceFilter.put("Cheap",new ArrayList<String>());
        priceFilter.put("Intermediate",new ArrayList<String>());
        priceFilter.put("Expensive",new ArrayList<String>());

        String restaurant = "Italian Breakfast Cheap";
        cuisineFilter.get("Italian").add(restaurant);
        foodTypeFilter.get("Breakfast").add(restaurant);
        priceFilter.get("Cheap").add(restaurant);

        restaurant = "Chinese Lunch Cheap";
        cuisineFilter.get("Chinese").add(restaurant);
        foodTypeFilter.get("Lunch").add(restaurant);
        priceFilter.get("Cheap").add(restaurant);

        restaurant = "Thai Dinner Expensive";
        cuisineFilter.get("Thai").add(restaurant);
        foodTypeFilter.get("Dinner").add(restaurant);
        priceFilter.get("Expensive").add(restaurant);

        restaurant = "French Lunch Intermediate";
        cuisineFilter.get("French").add(restaurant);
        foodTypeFilter.get("Lunch").add(restaurant);
        priceFilter.get("Intermediate").add(restaurant);

        restaurant = "Arabic Snack Cheap";
        cuisineFilter.get("Arabic").add(restaurant);
        foodTypeFilter.get("Snack").add(restaurant);
        priceFilter.get("Cheap").add(restaurant);

        restaurant = "Indian Dinner Intermediate";
        cuisineFilter.get("Indian").add(restaurant);
        foodTypeFilter.get("Dinner").add(restaurant);
        priceFilter.get("Intermediate").add(restaurant);

        restaurant = "Middle-East Lunch Expensive";
        cuisineFilter.get("Middle-East").add(restaurant);
        foodTypeFilter.get("Lunch").add(restaurant);
        priceFilter.get("Expensive").add(restaurant);

        restaurant = "Chinese Snack Expensive";
        cuisineFilter.get("Chinese").add(restaurant);
        foodTypeFilter.get("Snack").add(restaurant);
        priceFilter.get("Expensive").add(restaurant);

        restaurant = "Mexican Breakfast Intermediate";
        cuisineFilter.get("Mexican").add(restaurant);
        foodTypeFilter.get("Breakfast").add(restaurant);
        priceFilter.get("Intermediate").add(restaurant);

        return new RestaurantFilter(cuisineFilter, foodTypeFilter, priceFilter);
    }

    /**
     * Tests if the filter manager works for one cuisine preference
     */
    @Test
    public void filterManagerOneCuisinePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                Arrays.asList("Chinese Lunch Cheap","Chinese Snack Expensive"));
        RestaurantFilter restaurantFilter = RestaurantFilterTest.generateFilterManager();
        Assertions.assertEquals(obj, restaurantFilter.filter("No Preference", "Chinese", "No Preference", allRestaurants));
    }

    /**
     * Tests if the filter manager works for one price preference
     */
    @Test
    public void filterManagerOnePricePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                Arrays.asList("Italian Breakfast Cheap","Chinese Lunch Cheap", "Arabic Snack Cheap"));
        RestaurantFilter restaurantFilter = RestaurantFilterTest.generateFilterManager();
        Assertions.assertEquals(obj, restaurantFilter.filter("Cheap", "No Preference", "No Preference", allRestaurants));
    }

    /**
     * Tests if the filter manager works for one food type preference
     */
    @Test
    public void filterManagerOneFoodTypePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                Arrays.asList("Chinese Lunch Cheap", "French Lunch Intermediate", "Middle-East Lunch Expensive"));
        RestaurantFilter restaurantFilter = RestaurantFilterTest.generateFilterManager();
        Assertions.assertEquals(obj, restaurantFilter.filter("No Preference", "No Preference", "Lunch", allRestaurants));
    }

    /**
     * Tests if the filter manager works for food type and price preferences
     */
    @Test
    public void filterManagerPriceFoodTypePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("Mexican Breakfast Intermediate"));
        RestaurantFilter restaurantFilter = RestaurantFilterTest.generateFilterManager();
        Assertions.assertEquals(obj, restaurantFilter.filter("Intermediate", "No Preference", "Breakfast", allRestaurants));
    }

    /**
     * Tests if the filter manager works for cuisine and price preferences
     */
    @Test
    public void filterManagerPriceCuisinePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("Thai Dinner Expensive"));
        RestaurantFilter restaurantFilter = RestaurantFilterTest.generateFilterManager();
        Assertions.assertEquals(obj, restaurantFilter.filter("Expensive", "Thai", "No Preference", allRestaurants));
    }

    /**
     * Tests if the filter manager works for food type and cuisine preferences
     */
    @Test
    public void filterManagerFoodTypeCuisinePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("French Lunch Intermediate"));
        RestaurantFilter restaurantFilter = RestaurantFilterTest.generateFilterManager();
        Assertions.assertEquals(obj, restaurantFilter.filter("No Preference", "French", "Lunch", allRestaurants));
    }

    /**
     * Tests if the filter manager works for all preferences
     */
    @Test
    public void filterManagerAllPreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("Indian Dinner Intermediate"));
        RestaurantFilter restaurantFilter = RestaurantFilterTest.generateFilterManager();
        Assertions.assertEquals(obj, restaurantFilter.filter("Intermediate", "Indian", "Dinner", allRestaurants));
    }
}
