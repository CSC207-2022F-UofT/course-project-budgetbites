package controllers;

import usecases.RestaurantFilterDAI;
import gateways.MainMongoDB;
import usecases.filtering.restaurantfiltering.RestaurantFilter;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantFilterController {

    private final RestaurantFilterDAI restaurantFilterDAI = new MainMongoDB();
    private RestaurantFilter restaurantFilters;

    public RestaurantFilterController() {
        ArrayList<String> restaurants = restaurantFilterDAI.getAllRestaurants();
        HashMap<String, ArrayList<String>> cuisineFilter = new HashMap<>();
        cuisineFilter.put("Italian",new ArrayList<String>());
        cuisineFilter.put("Chinese",new ArrayList<String>());
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

        String cuisine;
        String price;
        String foodType;
        for (String r: restaurants) {
            cuisine = restaurantFilterDAI.getRestaurantAttribute(r, "cuisine");
            price = restaurantFilterDAI.getRestaurantAttribute(r, "priceRange");
            foodType = restaurantFilterDAI.getRestaurantAttribute(r, "foodType");
            cuisineFilter.get(cuisine).add(r);
            foodTypeFilter.get(foodType).add(r);
            priceFilter.get(price).add(r);
        }

        restaurantFilters = new RestaurantFilter(cuisineFilter,foodTypeFilter,priceFilter);
    }

    public ArrayList<String> getRestaurants(String pricePref, String cuisinePref, String foodTypePref) {
        if (pricePref.equals("No Preference") && cuisinePref.equals("No Preference") && foodTypePref.equals("No Preference")) {
            return restaurantFilterDAI.getAllRestaurants();
        } else {
            String priceChoice = pricePref.split("\\[",0)[0].strip();
            return restaurantFilters.filter(priceChoice, cuisinePref, foodTypePref);
        }
    }
}
