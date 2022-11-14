package Use_Cases;

import Entities.Budget;

import java.util.Scanner;

public class budgetManager extends Budget {

    public budgetManager(double initialBudget, double currentBudget) {
        super(initialBudget, currentBudget);
    }

    public void adjustMonthlyBudget(double newBudgetSize) {
        if (newBudgetSize > initialBudget) {
            initialBudget = newBudgetSize;
            double increasingDifference = newBudgetSize - initialBudget;
            currentBudget = currentBudget + increasingDifference;
        }
        if (newBudgetSize == initialBudget) {
            throw new IllegalArgumentException("Budget is the same");
        }
        else {
            initialBudget = newBudgetSize;
            double decreasingDifference = initialBudget - newBudgetSize;
            if (currentBudget > decreasingDifference) {
                currentBudget = currentBudget - decreasingDifference;
            } else
                throw new IllegalArgumentException("You've exceeded your monthly limit");
        }
    }

    public void orderedMealsBudget(){
        currentBudget = currentBudget + pastOrders;
    }

}