package usecases;
import entities.*;
import java.util.ArrayList;

public interface TestDAI {
    public void saveUser(User user);

    public void deleteUser (String username);

    public void saveRestaurant (Restaurant restaurant);

    public void deleteRestaurant (String restaurantName);

    public User findUserByUsername (String username);

    public Restaurant findRestaurantByRestaurantName (String restaurantName);

    public boolean userExists (String username);

    public PastOrders findPastOrders (String username);

    public boolean addToPastOrders (String username, Order order);

    public Object getUserAttribute (String username, String attribute);

    public String getRestaurantAttribute (String restaurantName, String attribute);

    public ArrayList<String> getAllRestaurants ();

    public ArrayList<String> getAllUsers ();

    public ArrayList<FoodItem> getMenu (String restaurantName);

    public void updateAttributeByUsername (String username, String attribute, Object attValue);

    public void updateAttributeByRestaurantName (String restaurantName, String attribute, Object attValue);
}
