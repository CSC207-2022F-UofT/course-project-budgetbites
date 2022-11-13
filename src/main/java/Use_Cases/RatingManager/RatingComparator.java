package Use_Cases.RatingManager;

import Entities.Restaurant;

import java.util.Comparator;

public class RatingComparator implements Comparator<Restaurant> {
    @Override
    public int compare (Restaurant r1, Restaurant r2) {
       if (r1.avgRating > r2.avgRating) return 1;
       else if (r1.avgRating == r2.avgRating) return 0;
       else return -1;
    }
}
