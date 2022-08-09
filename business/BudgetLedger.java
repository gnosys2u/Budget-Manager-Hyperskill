package business;

import java.util.ArrayList;
import java.util.List;

public class BudgetLedger {
    private List<BudgetItem> items;
    private double balanceInDollars;

    public BudgetLedger() {
        items = new ArrayList<>();
        balanceInDollars = 0.0;
    }

    public void addIncome(double incomeDollars) {
        balanceInDollars += incomeDollars;
    }

    public double getBalanceInDollars() {
        return balanceInDollars;
    }

    public void setBalanceInDollars(double dollars) {
        balanceInDollars = dollars;
    }

    public void addExpense(String description, String category, double costInDollars) {
        items.add(new BudgetItem(description, category, costInDollars));
        balanceInDollars -= costInDollars;
    }

    public List<BudgetItem> getExpenses() {
        return items;
    }
}
