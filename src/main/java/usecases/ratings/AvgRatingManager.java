package usecases.ratings;

import entities.Restaurant;

public class AvgRatingManager {
    private Restaurant reviewRestaurant;

    private int sumRatings = 0;
    private int numRatings = 0;
    private double avgRating;

    /**
     * Save the current restaurant for the which the average rating is to be calculated.
     * @param reviewRestaurant Current restaurant for the which the average rating is to be calculated.
     */
    private void saveRestaurant(Restaurant reviewRestaurant){
        this.reviewRestaurant = reviewRestaurant;
    }

    /**
     * Recalculate the length of the reviews array, and the sum of the review stars in current restaurant.
     */
    private void calculateRatingSumAndLength(){
        for(int i = 0; i<reviewRestaurant.reviews.size(); i++){
            this.sumRatings += (int) reviewRestaurant.reviews.get(i)[1];
            this.numRatings++;
        }
    }

    /**
     * Calculate the average rating by dividing the sum of ratings by the number of reviews.
     */
    public void calculateRatingAverage(){
        this.avgRating = sumRatings / numRatings;
    }

    /**
     * Saves the calculated average rating to the avgRating instance attribute of the restaurant
     */
    public void returnAvgRating(){
        reviewRestaurant.avgRating = avgRating;
    }

    /**
     * Setter method to set the SumRatings to i
     * @param i Value that is to be set to SumRatings.
     */
    public void setSumRatings(int i){
        this.sumRatings = i;
    }
    /**
     * Setter method to set the NumRatings to i
     * @param i Value that is to be set to NumRatings.
     */
    public void setNumRatings(int i){
        this.numRatings = i;
    }
    /**
     * Setter method to set the AvgRating to i
     * @param i Value that is to be set to AvgRating.
     */
    public void setAvgRating(double i){
        this.avgRating = i;
    }

    /**
     * Getter method to get the SumRatings.
     */
    public int getSumRatings(){
        return sumRatings;
    }
    /**
     * Getter method to get the NumRatings.
     */
    public int getNumRatings(){
        return numRatings;
    }
    /**
     * Getter method to get the AvgRating.
     */
    public double getAvgRating(){
        return avgRating;
    }
}
