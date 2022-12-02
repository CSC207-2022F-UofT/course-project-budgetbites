package entityTesting;

import entities.FoodItem;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FoodItemTest {

    // Test cases for the getter functionalities of the foodItems Entity
    //


    // Test case for the getItemCost getter function
    @Test
    public void getItemCostTest() {
        FoodItem foodItem = new FoodItem("Pizza", 10);
        double itemCost = 10;
        Assertions.assertEquals(itemCost, foodItem.getItemCost());
    }

    //Test case for the getItemName getter function
    @Test
    public void getItemNameTest() {
        FoodItem foodItem = new FoodItem("Pizza", 24);
        String itemName = "Pizza";
        Assertions.assertEquals(itemName, foodItem.getItemName());
    }
}
