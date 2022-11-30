package Use_Cases.Budgeting;

import Entities.Budget;
import Use_Cases.ItemCart.pastOrders;
import Use_Cases.mainMongoDB;

import java.util.Objects;
import java.util.Scanner;

public class budgetManager extends Budget {

    /**
     *      * The adjustMonthlyBudget method allows the user to voluntarily update their monthly budget whenever they would
     *      * like by inputting a new budget in the UI
     *      * @param newBudgetSize
     * @param budget
     * @param newBudgetSize
     */
    public void adjustMonthlyBudget(Budget budget, double newBudgetSize) {
        if (newBudgetSize < 0){
            throw new IllegalArgumentException("Monthly Budget cannot be less than zero");
        }
        if (newBudgetSize == budget.getInitialBudget()) {
            throw new IllegalArgumentException("Budget is the same");
        }
        if (newBudgetSize > budget.getInitialBudget()) {
            double increasingDifference = newBudgetSize - budget.getInitialBudget();
            budget.setInitialBudget(newBudgetSize);
            double newCurrentBudget = budget.getCurrentBudget() + increasingDifference;
            budget.setCurrentBudget(newCurrentBudget);
        }
        else {
            double decreasingDifference = budget.getInitialBudget() - newBudgetSize;
            budget.setInitialBudget(newBudgetSize);
            double newCBudget = budget.getCurrentBudget() - decreasingDifference;
            budget.setCurrentBudget(newCBudget);
        }
    }

    /**
     * orderedMealsBudget method automatically decreases the user's current budget when an order is placed
     * @param budget
     * @param orders
     */

    public void orderedMealsBudget(Budget budget, pastOrders orders) {
        double newCBudget = budget.getCurrentBudget() - orders.getCostOfLastOrdered();
        budget.setCurrentBudget(newCBudget);
    }
}
