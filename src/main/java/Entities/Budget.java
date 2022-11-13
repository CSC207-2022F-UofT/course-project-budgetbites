package Entities;

public class Budget {
    public double initialBudget;
    public double currentBudget;

    public Budget (double initialBudget, double currentBudget){
        this.initialBudget = initialBudget;
        this.currentBudget = currentBudget;
    }

    public double getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(double initialBudget) {
        if (initialBudget < 0)
            throw new IllegalArgumentException("Initial Budget cannot be less than 0");
        this.initialBudget = initialBudget;
    }

    public double getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(double currentBudget) {
        if (currentBudget < 0)
            throw new IllegalArgumentException("Current Budget cannot be less than 0");
        this.currentBudget = currentBudget;
    }
}
