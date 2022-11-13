package Use_Cases;

import Entities.Restaurant;

import java.util.ArrayList;

public class filterManager {
    private restaurantFilter cuisineFilter;

    private restaurantFilter foodTypeFilter;

    private restaurantFilter priceFilter;

    public filterManager(restaurantFilter cuisineFilter, restaurantFilter foodTypeFilter, restaurantFilter priceFilter) {
        this.cuisineFilter = cuisineFilter;
        this.foodTypeFilter = foodTypeFilter;
        this.priceFilter = priceFilter;
    }

    public ArrayList<Restaurant> filter(userPreferences prefs) {
        ArrayList<Restaurant> cuisineChoice = cuisineFilter.filter(prefs.getCuisinePreference());
        ArrayList<Restaurant> foodTypeChoice = foodTypeFilter.filter(prefs.getFoodTypePreference());
        ArrayList<Restaurant> priceChoice = priceFilter.filter((prefs.getPricePreference()));
        cuisineChoice.retainAll(foodTypeChoice);
        cuisineChoice.retainAll(priceChoice);
        return cuisineChoice;
    }
}
