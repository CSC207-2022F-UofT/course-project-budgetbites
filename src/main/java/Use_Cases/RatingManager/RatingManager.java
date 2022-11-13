package Use_Cases.RatingManager;

import Entities.Restaurant;
import Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RatingManager {
    private User reviewUser;
    private Restaurant reviewRestaurant;
    private Integer numStars;
    private String reviewText;

    private void saveUser(User reviewUser){
        this.reviewUser = reviewUser;
    }

    private void saveRestaurant(Restaurant reviewRestaurant){
        this.reviewRestaurant = reviewRestaurant;
    }

    private void getReviewStars(int numStars){
        this.numStars = numStars;
    }

    private void getReviewText(String reviewText){
        this.reviewText = reviewText;
    }

    private Object[] createReview(){
        Object[] arr = new Object[4];

        // Call saveUser, saveRestaurant, getReviewStars, getReviewText
        arr[0] = (this.reviewUser);
        arr[1] = (this.reviewRestaurant);
        arr[2] = (this.numStars);
        arr[3] = (this.reviewText);

        return arr;
    }

    private void uploadReview(){
        // Main method to upload review to restaurant

        this.reviewRestaurant.reviews.add(createReview());
    }


}
