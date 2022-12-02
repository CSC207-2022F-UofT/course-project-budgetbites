import entities.Budget;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;


public class budgetEntityTest {

    @Test
    public void getInitialBudgetTest() {

        Budget budget = new Budget(1000);
        double initialBudget = budget.getInitialBudget();

        Assertions.assertEquals(1000, initialBudget);
    }

    @Test
    public void getCurrentBudgetTest() {

        Budget budget = new Budget(1000);
        double currentBudget = budget.getCurrentBudget();

        Assertions.assertEquals(1000, currentBudget);
    }

    @Test
    public void setInitialBudgetTest() {

        Budget budget = new Budget(1000);
        budget.setInitialBudget(2022);

        Assertions.assertEquals(2022, budget.getInitialBudget());
    }

    @Test
    public void setCurrentBudgetTest() {

        Budget budget = new Budget(1000);
        budget.setCurrentBudget(2003);

        Assertions.assertEquals(2003, budget.getCurrentBudget());
    }

}