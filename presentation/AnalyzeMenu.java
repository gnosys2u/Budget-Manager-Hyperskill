package presentation;

import business.BudgetItem;
import business.BudgetService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static utils.Util.*;

public class AnalyzeMenu extends ConsoleMenu {
    static final int COMMAND_SORT_ALL = 1;
    static final int COMMAND_SORT_BY_TYPE = 2;
    static final int COMMAND_SORT_CERTAIN_TYPE = 3;
    static final int COMMAND_BACK = 4;

    public AnalyzeMenu(BudgetService budgetService) {
        super(budgetService);

        setTitle("How do you want to sort?");
        lowestChoice = 1;

        addChoice("Sort all purchases");
        addChoice("Sort by type");
        addChoice("Sort certain type");
        addChoice("Back");
    }
    @Override
    public void showChoices() {
        if (title != null) {
            prln(title);
        }

        for (int i = 0; i < choices.size(); ++i) {
            prln((i + 1) + ") " + choices.get(i));
        }
    }

    private void showExpenses(List<BudgetItem> expenses) {
        double totalCost = 0.0;

        for (BudgetItem expense : expenses) {
            totalCost += expense.getCostInDollars();
            prln(expense.asString());
        }
        prln("Total:" + BudgetItem.dollarsToString(totalCost));
    }

    void sortAndShowExpenses(String category, ArrayList<BudgetItem> expenses) {
        if (expenses.isEmpty()) {
            prln("The purchase list is empty!");
        } else {
            prln(category + ":");
            expenses.sort(Comparator.comparing(BudgetItem::getCostInDollars)
                    .reversed());
            showExpenses(expenses);
        }
    }

    void showExpenseTotalsByType() {
        List<BudgetItem> expenses = budgetService.getExpenses();
        double foodCost = 0.0;
        double clothesCost = 0.0;
        double entertainmentCost = 0.0;
        double otherCost = 0.0;

        for (BudgetItem expense : expenses) {
            String category = expense.getCategory().toLowerCase();
            double cost = expense.getCostInDollars();
            if ("food".equals(category)) {
                foodCost += cost;
            } else if ("clothes".equals(category)) {
                clothesCost += cost;
            } else if ("entertainment".equals(category)) {
                entertainmentCost += cost;
            } else if ("other".equals(category)) {
                otherCost += cost;
            } else {
                logitln("showExpenseTotalsByType - unknown category " + category);
            }
        }

        ArrayList<BudgetItem> expensesByType = new ArrayList<>();
        expensesByType.add(new BudgetItem("Food -", "", foodCost));
        expensesByType.add(new BudgetItem("Clothes -", "", clothesCost));
        expensesByType.add(new BudgetItem("Entertainment -", "", entertainmentCost));
        expensesByType.add(new BudgetItem("Other -", "", otherCost));
        sortAndShowExpenses("Types", expensesByType);
    }

    @Override
    protected void processChoice(int userChoice) {
        switch (userChoice) {
            case COMMAND_BACK:
                exitNow = true;
                break;

            case COMMAND_SORT_ALL: {
                ArrayList<BudgetItem> expenses = new ArrayList<>(budgetService.getExpenses());
                sortAndShowExpenses("All", expenses);
                break;
            }

            case COMMAND_SORT_BY_TYPE: {
                showExpenseTotalsByType();
                break;
            }

            case COMMAND_SORT_CERTAIN_TYPE: {
                CategoryMenu categoryMenu = new CategoryMenu(budgetService);
                categoryMenu.interact();
                String category = categoryMenu.getChosenCategory();
                ArrayList<BudgetItem> expenses = budgetService.getExpenses(category);
                sortAndShowExpenses(category, expenses);
                break;
            }

            default:
                handleBadChoice(userChoice);
                break;
        }
    }
}
