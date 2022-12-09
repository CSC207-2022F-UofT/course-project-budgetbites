package ratings;

import usecases.ratings.AvgRatingManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class avgRatingManagerTest {
    /**
     * Tests if an accurate average is given for a set of rating
     */
    @Test
    public void avgRatingManagerAverage(){

        AvgRatingManager arm  = new AvgRatingManager();
        arm.setSumRatings(10);
        arm.setNumRatings(2);
        arm.calculateRatingAverage();
        Assertions.assertEquals(5, arm.getAvgRating());
    }

}
