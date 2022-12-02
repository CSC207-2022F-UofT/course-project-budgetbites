import entities.Budget;
import entities.FoodItem;
import entities.Order;
import entities.PastOrders;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import usecases.budgeting.BudgetManager;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class budgetManagerTest {

    private PastOrders p1;
    private Order o1;
    private Order o2;
    private FoodItem f1;
    private FoodItem f2;
    private FoodItem f3;
    private FoodItem f4;
    private FoodItem f5;

    @Before
    public void init() {
        o1 = new Order(LocalDateTime.now().minusDays(2).toString(), "Food from East");
        o2 = new Order(LocalDateTime.now().minusDays(1).toString(), "Food from East");
        p1 = new PastOrders();

        f1 = new FoodItem("Chicken Shawarma", 8);
        f2 = new FoodItem("Hummus with Pita", 5);
        f3 = new FoodItem("Falafel Wrap", 4);
        f4 = new FoodItem("Beef Shawarma", 8);
        f5 = new FoodItem("Chicken Saj", 7);

        o1.addToOrder(f1);
        o1.addToOrder(f2);
        o2.addToOrder(f3);
        o2.addToOrder(f4);

        p1.addOrder(o1);
        p1.addOrder(o2);
    }

    @Test
    public void orderedMealsBudgetTest() {
        Budget budget = new Budget();
        BudgetManager budgetManager = new BudgetManager();
        budget.setCurrentBudget(200);
        HashMap<String, Order> pastOrdersMap = new HashMap<String, Order>();
        pastOrdersMap.put(o1.getDateOrdered(), o1);
        pastOrdersMap.put(o2.getDateOrdered(), o2);
        double price = p1.getCostOfLastOrdered();
        budgetManager.orderedMealsBudget(budget, p1);
        double currBudget = 200 - price;

        Assertions.assertEquals(currBudget, budget.getCurrentBudget());
    }

    @Test
    public void adjustBudgetNegativeErrorCaseTest() {
        boolean thrown = false;

        try {
            Budget budget = new Budget(500);
            BudgetManager budgetManager = new BudgetManager();
            budgetManager.adjustMonthlyBudget(budget, -120);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void adjustBudgetSameErrorCaseTest() {
        boolean thrown = false;

        try {
            Budget budget = new Budget(750);
            BudgetManager budgetManager = new BudgetManager();
            budgetManager.adjustMonthlyBudget(budget, 750);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void adjustBudgetDecreaseTest() {

        Budget budget = new Budget(1000);
        budget.setCurrentBudget(15);
        BudgetManager budgetManager = new BudgetManager();
        budgetManager.adjustMonthlyBudget(budget, 800);

        Assertions.assertEquals(800.0, budget.getInitialBudget());
        Assertions.assertEquals(-185.0, budget.getCurrentBudget());
    }

    @Test
    public void adjustBudgetIncreaseTest() {

        Budget budget = new Budget(1000);
        budget.setCurrentBudget(15);
        BudgetManager budgetManager = new BudgetManager();
        budgetManager.adjustMonthlyBudget(budget, 1200);

        Assertions.assertEquals(1200.0, budget.getInitialBudget());
        Assertions.assertEquals(215.0, budget.getCurrentBudget());
    }
}

