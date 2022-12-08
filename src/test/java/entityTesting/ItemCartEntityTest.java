package entityTesting;

import entities.FoodItem;
import entities.ItemCart;
import entities.PastOrders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemCartEntityTest {

    private ItemCart i1;
    private PastOrders p1;
    private FoodItem f1;
    private FoodItem f2;
    private FoodItem f3;

    /**
     * creates variables that will be needed to test OrderEntity
     */
    @Before
    public void init () {
        i1 = new ItemCart();
        p1 = new PastOrders();
        f1 = new FoodItem("Chicken Shawarma", 8);
        f2 = new FoodItem("Hummus with Pita", 5);
        f3 = new FoodItem("Falafel Wrap", 4);

        i1.addToCart(f1);
        i1.addToCart(f2);
    }

    /**
     * Deletes Falafel Wrap which costs $4 from the current order
     */
    @After
    public void teardown () {
        i1.getCurrentOrder().remove(f3);
    }

    /**
     * Tests if after the deletion a correct ArrayList is outputted
     */
    @Test
    public void getCurrentOrderTest () {
        ArrayList<FoodItem> cart = new ArrayList<FoodItem>(Arrays.asList(f1, f2));

        Assertions.assertEquals(cart, i1.getCurrentOrder());
    }

    /**
     * Tests if accurate ArrayList is outputted after added f3 to the list
     */
    @Test
    public void addToCartTest() {
        ArrayList<FoodItem> cart = new ArrayList<>(Arrays.asList(f1, f2, f3));
        i1.addToCart(f3);

        Assertions.assertEquals(cart, i1.getCurrentOrder());
    }

    /**
     * Tests if code can get the food names from the food object
     */
    @Test
    public void getFoodNamesTest () {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Chicken Shawarma", "Hummus with Pita"));
        ArrayList<String> itemNames = i1.getFoodNames();
        Assertions.assertEquals(expected, itemNames);
    }

    /**
     * Tests total cost by adding amount of cost of all food items
     */
    @Test
    public void getTotalCostTest () {
        double totalCost = 13;
        double actualCost = i1.getTotalCost();
        Assertions.assertEquals(totalCost, actualCost);
    }

    /**
     * Tests if a new order can accurately be made
     */
    @Test
    public void makeOrderTest() {
        i1.makeOrder(p1, "Food from East");

        // p1's pastOrdersMap is initially empty, so if makeOrder works, the size will increase by 1
        Assertions.assertEquals(p1.getPastOrdersMap().size(), 1);
        // The i1.currentOrder should be empty by the end of this method
        Assertions.assertEquals(new ArrayList<FoodItem>(), i1.getCurrentOrder());
    }
}