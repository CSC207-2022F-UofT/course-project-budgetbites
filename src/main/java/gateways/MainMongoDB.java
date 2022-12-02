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
import usecases.FilterManagerDAI;
import usecases.LoginDAI;
import usecases.SuggestionToUserDAI;

import java.util.ArrayList;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.nin;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class MainMongoDB implements SuggestionToUserDAI, LoginDAI, FilterManagerDAI {
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

//    Save new User or Restaurant to Database
    public void saveUser (User user) {
        userRepo.insertOne(user);
    }

    public void saveRestaurant (Restaurant restaurant) {
        restaurantRepo.insertOne(restaurant);
    }

//    Find User or Restaurant by username or restaurantname
    public User findUserByUsername (String username) {
        return userRepo.find(eq("username", username)).first();
    }

    public Restaurant findRestaurantByRestaurantName (String restaurantName) {
        return restaurantRepo.find(eq("restaurantName", restaurantName)).first();
    }

//    Check if user exists
    public boolean userExists (String username) {
        return userRepo.find(eq("username", username)).first() != null;
    }

//    Past Orders
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

    public boolean addToPastOrders (String username, Order order) {
        User user = userRepo.find(eq("username", username)).first();
        assert user != null;
        PastOrders pastOrders = user.getPastOrders();
        pastOrders.addOrder(order);

        Document query = new Document("username", username);
        Bson updates = Updates.set("PastOrders", pastOrders);
        UpdateOptions options = new UpdateOptions().upsert(true);

        userRepo.updateOne(query, updates, options);
        return true;
    }

    // Find attribute by username

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

    // Find attribute by Restaurant

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

    public ArrayList<String> getAllRestaurants () {
        ArrayList<String> restaurants = new ArrayList<String>();
        restaurantRepo.find().forEach(restaurant -> restaurants.add(restaurant.getRestaurantName()));
        return restaurants;
    }

    public ArrayList<String> getAllUsers () {
        ArrayList<String> users = new ArrayList<String>();
        userRepo.find().forEach(user -> users.add(user.getFirstName()));
        return users;
    }

    public Object getMenu (String restaurantName){
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

//    Update basic User or Restaurant attribute

    public void updateAttributeByUsername (String username, String attribute, Object attValue) {
        Document query = new Document("username", username);

        Bson updates = Updates.combine(
                Updates.set(attribute, attValue)
        );

        UpdateOptions options = new UpdateOptions().upsert(true);

        userRepo.updateOne(query, updates, options);
    }

    public void updateAttributeByRestaurantName (String restaurantName, String attribute, Object attValue) {
        Document query = new Document("restaurantName", restaurantName);
        Bson updates = Updates.combine(Updates.set(attribute, attValue));
        UpdateOptions options = new UpdateOptions().upsert(true);

        restaurantRepo.updateOne(query, updates, options);
    }
}