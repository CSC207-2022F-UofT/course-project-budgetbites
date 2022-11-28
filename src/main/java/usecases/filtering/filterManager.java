package usecases.filtering;

import entities.Restaurant;
import usecases.filtering.RestaurantFiltering.restaurantFilter;

import java.util.ArrayList;
import java.util.Objects;

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
        if (!Objects.isNull(cuisineChoice)) {
            if (!Objects.isNull(foodTypeChoice)) {
                cuisineChoice.retainAll(foodTypeChoice);
            }
            if (!Objects.isNull(priceChoice)) {
                cuisineChoice.retainAll(priceChoice);
            }
            return cuisineChoice;
        } else if (!Objects.isNull(foodTypeChoice)) {
            if (!Objects.isNull(priceChoice)) {
                foodTypeChoice.retainAll(priceChoice);
            }
            return foodTypeChoice;
        } else {
            return priceChoice;
        }
    }
}
