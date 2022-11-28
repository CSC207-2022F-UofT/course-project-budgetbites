package usecases.budgeting;

import entities.Budget;
import entities.PastOrders;

public class BudgetManager extends Budget {

    /**
     * The adjustMonthlyBudget method allows the user to voluntarily update their monthly budget whenever they would
     * like by inputting a new budget in the UI
     * @param newBudgetSize
     */
    public void adjustMonthlyBudget(double newBudgetSize) {
        if (newBudgetSize > initialBudget) {
            initialBudget = newBudgetSize;
            double increasingDifference = newBudgetSize - initialBudget;
            currentBudget = currentBudget + increasingDifference;
        }
        if (newBudgetSize == initialBudget) {
            throw new IllegalArgumentException("Budget is the same");
        } else {
            initialBudget = newBudgetSize;
            double decreasingDifference = initialBudget - newBudgetSize;
            if (currentBudget > decreasingDifference) {
                currentBudget = currentBudget - decreasingDifference;
            } else
                throw new IllegalArgumentException("You've exceeded your monthly limit");
        }
    }

    /**
     * orderedMealsBudget method automatically decreases the user's current budget when an order is placed
     * @param orders
     * @return double
     */

    public double orderedMealsBudget(PastOrders orders) {
        return currentBudget - orders.getCostOfLastOrdered();
    }
}
