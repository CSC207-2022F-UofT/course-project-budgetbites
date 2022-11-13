package Use_Cases;

public class userPreferences {
    private String pricePreference;

    private String cuisinePreference;

    private String foodTypePreference;

    public userPreferences() {
        pricePreference = null;
        cuisinePreference = null;
        foodTypePreference = null;
    }

    public void setCuisinePreference(String cuisinePreference) {
        this.cuisinePreference = cuisinePreference;
    }

    public void setFoodTypePreference(String foodTypePreference) {
        this.foodTypePreference = foodTypePreference;
    }

    public void setPricePreference(String pricePreference) {
        this.pricePreference = pricePreference;
    }

    public String getCuisinePreference() {
        return cuisinePreference;
    }

    public String getFoodTypePreference() {
        return foodTypePreference;
    }

    public String getPricePreference() {
        return pricePreference;
    }
}
