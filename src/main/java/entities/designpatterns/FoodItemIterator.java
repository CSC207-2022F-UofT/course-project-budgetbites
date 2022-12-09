package entities.designpatterns;

import entities.FoodItem;

public interface FoodItemIterator {
    /**
     * Returns whether there is another element in the list
     * @return True if there is another element in the list, False otherwise
     */
    boolean hasNext ();

    /**
     * Returns the next FoodItem
     * @return Returns a FoodItem which is representative of the next FoodItem in a given list
     */
    FoodItem next ();


}
