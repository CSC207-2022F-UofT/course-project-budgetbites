package gateways;

import entities.*;
import entities.PastOrders;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import entities.ItemCart;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import usecases.*;

import usecases.BudgetDAI;
import usecases.RestaurantFilterDAI;
import usecases.FoodItemsDAI;
import usecases.LoginDAI;
import usecases.SuggestionToUserDAI;

import java.util.ArrayList;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MainMongoDB implements SuggestionToUserDAI, LoginDAI, RestaurantFilterDAI, FoodItemsDAI, BudgetDAI, TestDAI, MakeOrderDAI, PastOrdersDAI {
//    Create all Class models
    static ClassModel<User> userClassModel = ClassModel.builder(User.class).enableDiscriminator(true).build();
    static ClassModel<Order> orderClassModel = ClassModel.builder(Order.class).enableDiscriminator(true).build();
    static ClassModel<PastOrders> pastOrdersClassModel = ClassModel.builder(PastOrders.class).enableDiscriminator(true).build();
    static ClassModel<FoodItem> foodItemClassModel = ClassModel.builder(FoodItem.class).enableDiscriminator(true).build();
    static ClassModel<Budget> budgetClassModel = ClassModel.builder(Budget.class).enableDiscriminator(true).build();
    static ClassModel<ItemCart> itemCartClassModel = ClassModel.builder(ItemCart.class).enableDiscriminator(true).build();
    static ClassModel<Object[]> objectClassModel = ClassModel.builder(Object[].class).enableDiscriminator(true).build();

//    Create the Codec Registry with all the class models to allow MongoDB to store custom Objects
    static CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder()
            .register(userClassModel).register(orderClassModel).register(pastOrdersClassModel).register(foodItemClassModel).register(budgetClassModel).register(itemCartClassModel).register(objectClassModel)
            .automatic(true).build()));
    static CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

//    Connect to MongoDB server to the budgetbites database
    static MongoClient client = MongoClients.create("mongodb+srv://budgetbites:budgetbites@cluster0.f1q9roc.mongodb.net/?retryWrites=true&w=majority");
    static MongoDatabase db = client.getDatabase("budgetbites").withCodecRegistry(codecRegistry);

//    Obtain the User and Restaurant collections
    static MongoCollection<Restaurant> restaurantRepo = db.getCollection("restaurants", Restaurant.class);
    static MongoCollection<User> userRepo = db.getCollection("users", User.class);

//    Methods

    /**
     * Saves a user object to the MongoDB database
     * @param user: The User object that should be stored on the database
     */
    public void saveUser(User user) {
        userRepo.insertOne(user);
    }

    /**
     * Deletes a user object from the MongoDB database
     * @param username: The username of the user that should be deleted from the database
     */
    public void deleteUser (String username) {
        userRepo.deleteOne(eq("username", username));
    }

    /**
     * Saves a restaurant object to the MongoDB database
     * @param restaurant: The Restaurant object that should be stored on the database
     */
    public void saveRestaurant (Restaurant restaurant) {
        restaurantRepo.insertOne(restaurant);
    }

    /**
     * Deletes a restaurant object from the MongoDB database
     * @param restaurantName: The name of the restaurant that should be deleted from the database
     */
    public void deleteRestaurant (String restaurantName) {
        restaurantRepo.deleteOne(eq("restaurantName", restaurantName));
    }

    /**
     * Returns a user from the database in the form of a User object, based on the username provided
     * @param username: The username of the user that should be found from the database
     * @return Returns a user from the database in the form of a User object
     */
    public User findUserByUsername (String username) {
        return userRepo.find(eq("username", username)).first();
    }

    /**
     * Returns a restaurant from the database in the form of a Restaurant object, based on the restaurant name provided
     * @param restaurantName: The name of the restaurant that should be found from the database
     * @return Returns a restaurant from the database in the form of a Restaurant object
     */
    public Restaurant findRestaurantByRestaurantName (String restaurantName) {
        return restaurantRepo.find(eq("restaurantName", restaurantName)).first();
    }

    /**
     * Checks whether a user exists or not on the database, through the unique username
     * @param username: The username of the user that should be checked whether it exists or not
     * @return Returns True if the user exists on the database, False otherwise
     */
    public boolean userExists (String username) {
        return userRepo.find(eq("username", username)).first() != null;
    }

    /**
     * Returns a PastOrders object of a User from the database in the form of a PastOrders object, based on the username provided
     * @param username: The username of the user for which the attribute must be found
     * @return Returns a PastOrders object of a User from the database in the form of a PastOrders object
     */
    public PastOrders findPastOrders (String username) {
        User user = userRepo.find(eq("username", username)).first();
        if (user != null) {
            PastOrders currentPastOrder = user.getPastOrders();
            try {
                return currentPastOrder;
            } catch (NullPointerException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Adds an order to the PastOrders of a particular user through the username
     * @param username: The username of the user for which the attribute must be updated
     * @param order: The order that needs to be added to the user's PastOrders
     * @return  Returns True after the order is added
     */
    public boolean addToPastOrders (String username, Order order) {
        User user = userRepo.find(eq("username", username)).first();
        assert user != null;
        PastOrders pastOrders = user.getPastOrders();
        pastOrders.addOrder(order);

        Document query = new Document("username", username);
        Bson updates = Updates.set("pastOrders", pastOrders);
        UpdateOptions options = new UpdateOptions().upsert(true);

        userRepo.updateOne(query, updates, options);
        return true;
    }

    /**
     * Finds and returns a user instance attribute from the database, based on the username and attribute provided
     * @param username: The username of the user for which the attribute must be found
     * @param attribute: The name of the attribute that is required (budget, firstName, lastName, password, username)
     * @return Returns the attribute of the username that was passed, Budget object if attribute is budget, String otherwise
     */
    public Object getUserAttribute (String username, String attribute) {
        User user  = userRepo.find(eq("username", username)).first();
        assert user !=  null;
        switch (attribute) {
            case "budget":
                return user.getBudget();
            case "firstName":
                return user.getFirstName();
            case "lastName":
                return user.getLastName();
            case "password":
                return user.getPassword();
            case "username":
                return user.getUsername();
        }
        return null;
    }


    public Budget getUserBudget (String username) {
        User user  = userRepo.find(eq("username", username)).first();
        assert user !=  null;
        return user.getBudget();
    }


    /**
     * Finds and returns a restaurant instance attribute from the database, based on the restaurant name and attribute provided
     * @param restaurantName: The name of the restaurant for which the attribute must be found
     * @param attribute: The name of the attribute that is required (restaurantName, cuisine, priceRange, foodType, avgRating)
     * @return Returns the attribute of the restaurant as a String
     */

    public String getRestaurantAttribute (String restaurantName, String attribute) {
        Restaurant restaurant = restaurantRepo.find(eq("restaurantName", restaurantName)).first();
        assert restaurant != null;
        switch (attribute) {
            case "restaurantName":
                return restaurant.getRestaurantName();
            case "cuisine":
                return restaurant.getCuisine();
            case "priceRange":
                return restaurant.getPriceRange();
            case "foodType":
                return restaurant.getFoodType();
            case "avgRating":
                return String.valueOf(restaurant.getAvgRating());
        }
        return null;
    }

    /**
     * Finds and returns a list of all the names of the restaurants on the database
     * @return Returns an ArrayList<String> of all the restaurants names stored on the database
     */
    public ArrayList<String> getAllRestaurants () {
        ArrayList<String> restaurants = new ArrayList<>();
        restaurantRepo.find().forEach(restaurant -> restaurants.add(restaurant.getRestaurantName()));
        return restaurants;
    }

    /**
     * Finds and returns a list of all the first names of the users on the database
     * @return Returns an ArrayList<String> of all the user's first names stored on the database
     */
    public ArrayList<String> getAllUsers () {
        ArrayList<String> users = new ArrayList<>();
        userRepo.find().forEach(user -> users.add(user.getFirstName()));
        return users;
    }

    /**
     * Finds and returns the menu of a restaurant based on the restaurant name if the restaurant exists
     * @param restaurantName: The name of the restaurant for which the attribute must be found
     * @return Returns the menu of the restaurant as an ArrayList<FoodItem>
     */
    public ArrayList<FoodItem> getMenu (String restaurantName){
        Restaurant res = restaurantRepo.find(eq("restaurantName", restaurantName)).first();
        if (res != null) {
            ArrayList<FoodItem> menu = res.getMenu();
            try {
                return menu;
            } catch (NullPointerException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Updates a users instance attribute on the database, based on the username, attribute and attvalue provided
     * @param username: The username of the user for which the attribute must be updated
     * @param attribute: The name of the attribute that is required (budget, firstName, lastName, password, username)
     * @param attValue: The new value of the attribute that should be updated to the user
     */
    public void updateAttributeByUsername (String username, String attribute, Object attValue) {
        Document query = new Document("username", username);

        Bson updates = Updates.combine(
                Updates.set(attribute, attValue)
        );

        UpdateOptions options = new UpdateOptions().upsert(true);

        userRepo.updateOne(query, updates, options);
    }

    /**
     * Updates a restaurants instance attribute on the database, based on the restaurant name, attribute and attvalue provided
     * @param restaurantName: The name of the restaurant for which the attribute must be updated
     * @param attribute: The name of the attribute that is required (restaurantName, cuisine, priceRange, foodType, avgRating)
     * @param attValue: The new value of the attribute that should be updated to the restaurant
     */
    public void updateAttributeByRestaurantName (String restaurantName, String attribute, Object attValue) {
        Document query = new Document("restaurantName", restaurantName);
        Bson updates = Updates.combine(Updates.set(attribute, attValue));
        UpdateOptions options = new UpdateOptions().upsert(true);

        restaurantRepo.updateOne(query, updates, options);
    }
}