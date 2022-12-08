import entities.FoodItem;
import entities.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entities.*;
import org.junit.jupiter.api.Assertions;
import gateways.MainMongoDB;
import usecases.TestDAI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class MainMongoDBTest {

    private final TestDAI testDAI = new MainMongoDB();

    private User user;
    private User persistentUser;
    private Order o3;
    private Restaurant restaurant;
    private Restaurant persistentRestaurant;

    @Before
    public void init () {
        Order o1 = new Order(LocalDateTime.now().minusDays(2).toString(), "Food from East");
        Order o2 = new Order(LocalDateTime.now().minusDays(1).toString(), "Food from East");
        o3 = new Order(LocalDateTime.now().toString(), "Food from East");
        PastOrders p1 = new PastOrders();

        FoodItem f1 = new FoodItem("Chicken Shawarma", 8);
        FoodItem f2 = new FoodItem("Hummus with Pita", 5);
        FoodItem f3 = new FoodItem("Falafel Wrap", 4);
        FoodItem f4 = new FoodItem("Beef Shawarma", 8);
        FoodItem f5 = new FoodItem("Chicken Saj", 7);
        ArrayList<FoodItem> menu = new ArrayList<>(Arrays.asList(f1, f2, f3, f4, f5));

        o1.addToOrder(f1);
        o1.addToOrder(f2);
        o2.addToOrder(f3);
        o2.addToOrder(f4);
        o3.addToOrder(f1);
        o3.addToOrder(f2);
        o3.addToOrder(f3);
        o3.addToOrder(f4);
        o3.addToOrder(f5);

        p1.addOrder(o1);
        p1.addOrder(o2);

        Budget budget = new Budget(100);

        user = new User("Mongo", "Test", "MongoTest", "MongoTest@123", p1, budget);
        restaurant = new Restaurant("Mongo Test Restaurant", "Expensive", "Italian", "Lunch",5,  menu);

        persistentUser = new User("Persistent", "Mongo", "persistentUser", "persistentUser", p1, budget);
        persistentRestaurant = new Restaurant("Persistent Restaurant", "Expensive", "Indian", "Dinner", 5, menu);
    }

    @After
    public void teardown () {
        testDAI.deleteUser("MongoTest");
        testDAI.deleteRestaurant("Mongo Test Restaurant");

        testDAI.deleteUser(persistentUser.getUsername());
        testDAI.saveUser(persistentUser);

        testDAI.deleteRestaurant("Persistent Restaurant");
        testDAI.saveRestaurant(persistentRestaurant);

    }


    @Test
    public void saveUserTest() {
//        Object ID is changed when object is saved on database, so we will check whether the attribute is the same
        testDAI.saveUser(user);

        Assertions.assertEquals(testDAI.findUserByUsername("MongoTest").getFirstName(), user.getFirstName());
    }

    @Test
    public void saveRestaurantTest() {
        testDAI.saveRestaurant(restaurant);

        Assertions.assertEquals(testDAI.findRestaurantByRestaurantName("Mongo Test Restaurant").getRestaurantName(), restaurant.getRestaurantName());
    }

    @Test
    public void userExistsTest() {
        Assertions.assertTrue(testDAI.userExists("persistentUser"));
        Assertions.assertFalse(testDAI.userExists("falseUser"));
    }

    @Test
    public void getUserAttributeTest() {
        Assertions.assertEquals(testDAI.getUserAttribute("persistentUser", "username"), "persistentUser");
        Assertions.assertEquals(testDAI.getUserAttribute("persistentUser", "firstName"), "Persistent");
        Assertions.assertEquals(testDAI.getUserAttribute("persistentUser", "lastName"), "Mongo");
        Assertions.assertEquals(testDAI.getUserAttribute("persistentUser", "password"), "persistentUser");
    }

    @Test
    public void getRestaurantAttributeTest() {
        Assertions.assertEquals(testDAI.getRestaurantAttribute("Persistent Restaurant", "restaurantName"), "Persistent Restaurant");
        Assertions.assertEquals(testDAI.getRestaurantAttribute("Persistent Restaurant", "cuisine"), "Indian");
        Assertions.assertEquals(testDAI.getRestaurantAttribute("Persistent Restaurant", "foodType"), "Dinner");
        Assertions.assertEquals(testDAI.getRestaurantAttribute("Persistent Restaurant", "priceRange"), "Expensive");
        Assertions.assertEquals(testDAI.getRestaurantAttribute("Persistent Restaurant", "avgRating"), "5.0");
    }

    @Test
    public void getMenuTest() {
//        Cannot compare the ArrayList directly because of different object ids, so we are confirming whether the list of food item names are the same
        ArrayList<String> localFoodNames = new ArrayList<>(
                Arrays.asList("Chicken Shawarma", "Hummus with Pita", "Falafel Wrap", "Beef Shawarma", "Chicken Saj"));

        ArrayList<String> mongoFoodNames = new ArrayList<>();
        for (FoodItem foodItem : testDAI.getMenu("Persistent Restaurant")) {
            mongoFoodNames.add(foodItem.getItemName());
        }

        Assertions.assertEquals(localFoodNames, mongoFoodNames);
    }

    @Test
    public void updateAttributeByUsernameTest() {
        testDAI.updateAttributeByUsername("persistentUser", "firstName", "testchange");

        Assertions.assertEquals(testDAI.getUserAttribute("persistentUser", "firstName"), "testchange");
    }

    @Test
    public void findPastOrdersTest() {
        PastOrders pastOrders = testDAI.findPastOrders("persistentUser");

//        From the database, we can see the cost of the last ordered is 12, and the number of orders in the map is 2
        Assertions.assertEquals(pastOrders.getCostOfLastOrdered(), 12);
        Assertions.assertEquals(pastOrders.getPastOrdersMap().size(), 2);
        Assertions.assertEquals(pastOrders.getTotalCost(), 25);
    }

    @Test
    public void addToPastOrdersTest() {
        testDAI.addToPastOrders("persistentUser", o3);
        PastOrders pastOrders = testDAI.findPastOrders("persistentUser");

//        The new cost of last ordered is 17, and there are now 3 orders in pastOrdersMap
        Assertions.assertEquals(pastOrders.getCostOfLastOrdered(), 32);
        Assertions.assertEquals(pastOrders.getPastOrdersMap().size(), 3);
        Assertions.assertEquals(pastOrders.getTotalCost(), 57);
    }
}