package controllers;

import gateways.MainMongoDB;
import usecases.FoodItemsDAI;
import usecases.itemcart.ItemCartEditor;
import usecases.makeorder.MakeOrderInteractor;

import java.util.ArrayList;

public class ItemCartAndOrderController {
    private ItemCartEditor itemCartEditor;
    private MakeOrderInteractor makeOrderInteractor;

    private final FoodItemsDAI foodItemsDAI = new MainMongoDB();

    public ItemCartAndOrderController(String restaurantName) {
        itemCartEditor = new ItemCartEditor(foodItemsDAI.getMenu(restaurantName));
        makeOrderInteractor = new MakeOrderInteractor();
    }

    public void addToItemCart(String choice) {
        String[] split = choice.split("-", 0);
        String foodName = split[0].strip();
        itemCartEditor.addFood(foodName);
    }

    public void removeFromItemCart(String choice) {
        String[] split = choice.split("-", 0);
        String foodName = split[0].strip();
        itemCartEditor.removeFood(foodName);
    }

    public ArrayList<String> getItems() {
        return itemCartEditor.getFoodInItemCart();
    }

    public String getItemCartCost() {
        return itemCartEditor.getItemCartCost() + "";
    }

    public void makeOrder(String restaurantName, String username) {
        makeOrderInteractor.makeNewOrder(restaurantName, itemCartEditor.getItemCart(), username);
    }
}
