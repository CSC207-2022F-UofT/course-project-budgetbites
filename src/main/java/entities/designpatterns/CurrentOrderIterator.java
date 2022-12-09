package entities.designpatterns;

import entities.FoodItem;

import java.util.ArrayList;

public class CurrentOrderIterator implements FoodItemIterator {

    private int position = 0;
    private final ArrayList<FoodItem> currentOrder;

    /**
     * CurrentOrderIterator Constructor, Instantiates currentOrder from the parameter
     * @param currentOrder: the current order that should be iterated upon
     */
    public CurrentOrderIterator (ArrayList<FoodItem> currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Checks whether there is a next item in currentOrder
     * @return Returns True if there is a next item, False otherwise
     */
    public boolean hasNext() {
        return this.position < this.currentOrder.size();
    }

    /**
     * Returns the next item in the currentOrder, and increases the position by 1
     * @return Returns the item stored in currentOrder at index position, and is of type FoodItem
     */
    public FoodItem next() {
        FoodItem currItem = this.currentOrder.get(position);
        this.position += 1;
        return currItem;
    }
}
