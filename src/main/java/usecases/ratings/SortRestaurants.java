package usecases.ratings;

import entities.Restaurant;

import java.util.Arrays;

/**
 * Sort the list of restaurants based on the RatingComparator, which sorts based on the average rating each restaurant has recieved.
 */
public class SortRestaurants {
    private Restaurant[] SortRestaurants(Restaurant[] r){
        Arrays.sort(r, new RatingComparator());
        return r;
    }
}
