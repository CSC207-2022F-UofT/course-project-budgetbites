package entityTesting;

import entities.FoodItem;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FoodItemTest {

    /**
     * Tests the FoodItem Entity by calling an item and seeing if it returns the accurate corresponding price
     */
    @Test
    public void getItemCostTest() {
        FoodItem foodItem = new FoodItem("Pizza", 10);
        double itemCost = 10;
        Assertions.assertEquals(itemCost, foodItem.getItemCost());
    }


    /**
     * Tests for getItemName through a getter function
     */
    @Test
    public void getItemNameTest() {
        FoodItem foodItem = new FoodItem("Pizza", 24);
        String itemName = "Pizza";
        Assertions.assertEquals(itemName, foodItem.getItemName());
    }
}
