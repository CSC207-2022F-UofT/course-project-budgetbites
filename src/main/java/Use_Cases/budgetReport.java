package Use_Cases;

import java.text.NumberFormat;

public class budgetReport {

    public void printBudgetReport() {
        System.out.println();
        System.out.println("BUDGET REPORT FOR THE MONTH");
        System.out.println("----------------");
        for (short month = 1; month <= calculator.getYears() * 12; month++) {
            double balance = calculator.calculateBalance(month);
            System.out.println(currency.format(balance));
        }
    }
}


