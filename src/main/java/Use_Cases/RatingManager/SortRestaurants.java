package Use_Cases.RatingManager;

import Entities.Restaurant;

import java.util.Arrays;
import java.util.List;

public class SortRestaurants {
    private Restaurant[] SortRestaurants(Restaurant[] r){
        Arrays.sort(r, new RatingComparator());
        return r;
    }
}
