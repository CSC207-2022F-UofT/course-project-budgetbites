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

    // Test cases for the getter functionalities of the Order Entity

    private ItemCart i1;
    private PastOrders p1;
    private FoodItem f1;
    private FoodItem f2;
    private FoodItem f3;

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

    @After
    public void teardown () {
        i1.getCurrentOrder().remove(f3);
    }

    @Test
    public void getCurrentOrderTest () {
        ArrayList<FoodItem> cart = new ArrayList<FoodItem>(Arrays.asList(f1, f2));

        Assertions.assertEquals(cart, i1.getCurrentOrder());
    }

    @Test
    public void addToCartTest() {
        ArrayList<FoodItem> cart = new ArrayList<>(Arrays.asList(f1, f2, f3));
        i1.addToCart(f3);

        Assertions.assertEquals(cart, i1.getCurrentOrder());
    }

    @Test
    public void getFoodNamesTest () {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Chicken Shawarma", "Hummus with Pita"));
        ArrayList<String> itemNames = i1.getFoodNames();
        Assertions.assertEquals(expected, itemNames);
    }

    @Test
    public void getTotalCostTest () {
        double totalCost = 13;
        double actualCost = i1.getTotalCost();
        Assertions.assertEquals(totalCost, actualCost);
    }

    @Test
    public void makeOrderTest() {
        i1.makeOrder(p1, "Food from East");

//        p1's pastOrdersMap is initially empty, so if makeOrder works, the size will increase by 1
        Assertions.assertEquals(p1.getPastOrdersMap().size(), 1);
//        The i1.currentOrder should be empty by the end of this method
        Assertions.assertEquals(new ArrayList<FoodItem>(), i1.getCurrentOrder());
    }
}