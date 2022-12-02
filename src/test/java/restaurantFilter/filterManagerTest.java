package restaurantFilter;

import usecases.filtering.restaurantfiltering.FilterManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class filterManagerTest {

    public static FilterManager generateFilterManager() {
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

        return new FilterManager(cuisineFilter, foodTypeFilter, priceFilter);
    }

    @Test
    public void filterManagerOneCuisinePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                Arrays.asList("Chinese Lunch Cheap","Chinese Snack Expensive"));
        FilterManager filterManager = filterManagerTest.generateFilterManager();
        Assertions.assertEquals(obj, filterManager.filter("No Preference", "Chinese", "No Preference"));
    }

    @Test
    public void filterManagerOnePricePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                Arrays.asList("Italian Breakfast Cheap","Chinese Lunch Cheap", "Arabic Snack Cheap"));
        FilterManager filterManager = filterManagerTest.generateFilterManager();
        Assertions.assertEquals(obj, filterManager.filter("Cheap", "No Preference", "No Preference"));
    }

    @Test
    public void filterManagerOneFoodTypePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                Arrays.asList("Chinese Lunch Cheap", "French Lunch Intermediate", "Middle-East Lunch Expensive"));
        FilterManager filterManager = filterManagerTest.generateFilterManager();
        Assertions.assertEquals(obj, filterManager.filter("No Preference", "No Preference", "Lunch"));
    }

    @Test
    public void filterManagerPriceFoodTypePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("Mexican Breakfast Intermediate"));
        FilterManager filterManager = filterManagerTest.generateFilterManager();
        Assertions.assertEquals(obj, filterManager.filter("Intermediate", "No Preference", "Breakfast"));
    }

    @Test
    public void filterManagerPriceCuisinePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("Thai Dinner Expensive"));
        FilterManager filterManager = filterManagerTest.generateFilterManager();
        Assertions.assertEquals(obj, filterManager.filter("Expensive", "Thai", "No Preference"));
    }

    @Test
    public void filterManagerFoodTypeCuisinePreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("French Lunch Intermediate"));
        FilterManager filterManager = filterManagerTest.generateFilterManager();
        Assertions.assertEquals(obj, filterManager.filter("No Preference", "French", "Lunch"));
    }

    @Test
    public void filterManagerAllPreference() {
        ArrayList<String> obj = new ArrayList<String>(
                List.of("Indian Dinner Intermediate"));
        FilterManager filterManager = filterManagerTest.generateFilterManager();
        Assertions.assertEquals(obj, filterManager.filter("Intermediate", "Indian", "Dinner"));
    }
}
