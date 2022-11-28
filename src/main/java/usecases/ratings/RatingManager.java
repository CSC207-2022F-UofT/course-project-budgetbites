package usecases.ratings;

import entities.Restaurant;
import entities.User;

public class RatingManager {
    private User reviewUser;
    private Restaurant reviewRestaurant;
    private Integer numStars;
    private String reviewText;

    /**
     * Saves the current user to this.reviewUser
     * @param reviewUser User to be saved.
     */
    private void saveUser(User reviewUser){
        this.reviewUser = reviewUser;
    }

    /**
     * Saves the Restaurant for which the review is to be written
     * @param reviewRestaurant Restaurant to be saved.
     */
    private void saveRestaurant(Restaurant reviewRestaurant){
        this.reviewRestaurant = reviewRestaurant;
    }

    /**
     * Saves the number of stars the restaurant has received.
     * @param numStars Number of stars the restaurant has received.
     */
    private void getReviewStars(int numStars){
        this.numStars = numStars;
    }

    /**
     * Saves the Review Text the restaurant has received.
     * @param reviewText Review Text the restaurant has received.
     */
    private void getReviewText(String reviewText){
        this.reviewText = reviewText;
    }

    /**
     * Creates an array, which contains the reviewUser, reviewRestaurant, numStars & the reviewText.
     * @return The array as described above
     */
    private Object[] createReview(){
        Object[] arr = new Object[4];

        // Call saveUser, saveRestaurant, getReviewStars, getReviewText
        arr[0] = (this.reviewUser);
        arr[1] = (this.reviewRestaurant);
        arr[2] = (this.numStars);
        arr[3] = (this.reviewText);

        return arr;
    }

    /**
     * Saves the array from createReview to the reviews instance attribute of the restaurant.
     */
    private void uploadReview(){
        // Main method to upload review to restaurant

        this.reviewRestaurant.reviews.add(createReview());
    }


}
