package business;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.Util.*;

public class BudgetService {

    private BudgetLedger ledger;

    public BudgetService() {
        ledger = new BudgetLedger();
    }

    public void addIncome(double dollars) {
        ledger.addIncome(dollars);
    }

    public void addExpense(String description, String category, double costInDollars) {
        ledger.addExpense(description, category, costInDollars);
        logit("addExpense " + category + ": " + description + " $" + costInDollars + "  balance: " + getBalance());
    }

    public List<BudgetItem> getExpenses() {
        return ledger.getExpenses();
    }

    public ArrayList<BudgetItem> getExpenses(String category) {
        ArrayList<BudgetItem> result = new ArrayList<>();
        for (BudgetItem expense : ledger.getExpenses()) {
            if (expense.getCategory().equals(category)) {
                result.add(expense);
            }
        }
        return  result;
    }

    public double getBalance() {
        return ledger.getBalanceInDollars();
    }

    public void saveLedger() {
        try (PrintWriter pw = new PrintWriter(new File("purchases.txt"))) {
            pw.println(ledger.getBalanceInDollars());
            for (BudgetItem expense : ledger.getExpenses()) {
                pw.println(expense.getCategory());
                pw.println(expense.getDescription());
                pw.println(expense.getCostInDollars());
            }
        } catch (Exception ex) {
            logitln("saveLedger:" + ex.getMessage());
        }
    }

    public void loadLedger() {
        try (Scanner scanner = new Scanner(new File("purchases.txt"))) {
            ledger = new BudgetLedger();
            double balance = Double.parseDouble(scanner.nextLine());

            while (scanner.hasNextLine()) {
                String category = scanner.nextLine();
                String description = scanner.nextLine();
                double cost = Double.parseDouble(scanner.nextLine());
                ledger.addExpense(description, category, cost);
            }

            ledger.setBalanceInDollars(balance);
        } catch (Exception ex) {
            logitln("saveLedger:" + ex.getMessage());
        }
    }
}
