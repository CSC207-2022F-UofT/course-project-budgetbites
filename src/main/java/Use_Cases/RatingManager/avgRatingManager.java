package Use_Cases.RatingManager;

import Entities.Restaurant;

public class avgRatingManager {
    private Restaurant reviewRestaurant;

    private int sumRatings = 0;
    private int numRatings = 0;
    private double avgRating;

    private void saveRestaurant(Restaurant reviewRestaurant){
        this.reviewRestaurant = reviewRestaurant;
    }

    private void calculateRatingSumAndLength(){
        for(int i = 0; i<reviewRestaurant.reviews.size(); i++){
            this.sumRatings += (int) reviewRestaurant.reviews.get(i)[1];
            this.numRatings++;
        }
    }

    private void calculateRatingAverage(){
        this.avgRating = sumRatings / numRatings;
    }

    private void returnAvgRating(){
        reviewRestaurant.avgRating = avgRating;
    }
}
