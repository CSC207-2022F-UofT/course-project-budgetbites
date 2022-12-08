package entityTesting;

import entities.FoodItem;
import entities.Order;
import entities.PastOrders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PastOrdersEntityTest {

    /**
     * Test cases for the getter functionalities of the Order Entity
     */
    private PastOrders p1;
    private Order o1;
    private Order o2;
    private Order o3;
    private FoodItem f1;
    private FoodItem f2;
    private FoodItem f3;
    private FoodItem f4;
    private FoodItem f5;

    /**
     * gives all instance attribute values and creates order
     */
    @Before
    public void init () {
        o1 = new Order(LocalDateTime.now().minusDays(2).toString(), "Food from East");
        o2 = new Order(LocalDateTime.now().minusDays(1).toString(), "Food from East");
        p1 = new PastOrders();

        f1 = new FoodItem("Chicken Shawarma", 8);
        f2 = new FoodItem("Hummus with Pita", 5);
        f3 = new FoodItem("Falafel Wrap", 4);
        f4 = new FoodItem("Beef Shawarma", 8);
        f5 = new FoodItem("Chicken Saj", 7);

        o1.addToOrder(f1);
        o1.addToOrder(f2);
        o2.addToOrder(f3);
        o2.addToOrder(f4);

        p1.addOrder(o1);
        p1.addOrder(o2);
    }

    /**
     * Tests if accurate pastOrder list is being outputed through an ArrayList
     */
    @Test
    public void getPastOrdersMapTest() {
        HashMap<String, Order> pastOrdersMap = new HashMap<String, Order>();
        pastOrdersMap.put(o1.getDateOrdered(), o1);
        pastOrdersMap.put(o2.getDateOrdered(), o2);

        Assertions.assertEquals(pastOrdersMap, p1.getPastOrdersMap());
    }

    /**
     * Check is the last order is accurately outputted
     */
    @Test
    public void getLastOrderedTest() {
        String lastOrdered = o2.getDateOrdered();

        Assertions.assertEquals(lastOrdered, p1.getLastOrdered());
    }

    /**
     * Tests if acurrate order is given if searched by date of order
     */
    @Test
    public void getOrderByDateTest() {
        Assertions.assertEquals(o1, p1.getOrderByDate(o1.getDateOrdered()));
    }

    /**
     * Tests if acurrate food item is given if searched by date of order
     */
    @Test
    public void getOrderedItemsByDateTest() {
        ArrayList<FoodItem> orderedItems = o1.getOrderedItems();

        Assertions.assertEquals(orderedItems, p1.getOrderedItemsByDate(o1.getDateOrdered()));
    }

    /**
     * Tests if correct sum of orders outputted
     */
    @Test
    public void getTotalCostTest() {
        double totalCost = o1.getTotalCost() + o2.getTotalCost();

        Assertions.assertEquals(totalCost, p1.getTotalCost());
    }

    /**
     * Tests if true value of last order cost is given
     */
    @Test
    public void getCostOfLastOrderedTest() {
        double costOfLastOrdered = o2.getTotalCost();

        Assertions.assertEquals(costOfLastOrdered, p1.getCostOfLastOrdered());
    }

    /**
     * Tests if order is able to be added to all past order
     */
    @Test
    public void addOrderTest() {
        int pastOrdersMapLengthBefore = p1.getPastOrdersMap().size();
        o3 = new Order(LocalDateTime.now().toString(), "Food from East");
        o3.addToOrder(f5);
        p1.addOrder(o3);

        Assertions.assertEquals(pastOrdersMapLengthBefore + 1, p1.getPastOrdersMap().size());
        Assertions.assertEquals(o3.getDateOrdered(), p1.getLastOrdered());
    }
}