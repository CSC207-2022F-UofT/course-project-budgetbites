package entities;

public class Budget {
    protected double initialBudget;
    protected double currentBudget;

    public Budget () {
    }

    public Budget (double initialBudget){
        this.initialBudget = initialBudget;
        this.currentBudget = initialBudget;
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