package usecases.itemcart;

import entities.FoodItem;
import entities.ItemCart;

import java.util.ArrayList;

public class ItemCartEditor {
    private ItemCart itemCart;
    private ArrayList<FoodItem> menu;
    
    public ItemCartEditor(ArrayList<FoodItem> menu) {
        this.menu = menu;
        itemCart = new ItemCart();
    }

    public ItemCart getItemCart() {
        return itemCart;
    }
    
    public void addFood(String foodName){
        for (FoodItem food: menu) {
            if (food.getItemName().equals(foodName)) {
                itemCart.addToCart(food);
            }
        }
    }

    public void removeFood(String foodName){
        for (FoodItem food: menu) {
            if (food.getItemName().equals(foodName)) {
                itemCart.removeFromCart(food);
            }
        }
    }

    public ArrayList<String> getFoodInItemCart() {
        ArrayList<String> result = new ArrayList<>();
        for (FoodItem food:
        itemCart.getCurrentOrder()) {
            result.add(food.getItemName());
        }
        return result;
    }

    public double getItemCartCost() {
        double result = 0;
        for (FoodItem food :itemCart.getCurrentOrder()) {
            result += food.getItemCost();
        }
        return result;
    }
}