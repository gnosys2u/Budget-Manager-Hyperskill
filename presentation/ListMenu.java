package presentation;

import business.BudgetItem;
import business.BudgetService;

import java.util.List;

import static utils.Util.logitln;
import static utils.Util.*;

public class ListMenu extends ConsoleMenu {
    static final int COMMAND_FOOD = 1;
    static final int COMMAND_CLOTHES = 2;
    static final int COMMAND_ENTERTAINMENT = 3;
    static final int COMMAND_OTHER = 4;
    static final int COMMAND_ALL = 5;
    static final int COMMAND_BACK = 6;

    public ListMenu(BudgetService budgetService) {
        super(budgetService);

        setTitle("Choose the type of purchases");
        lowestChoice = 1;

        addChoice("Food");
        addChoice("Clothes");
        addChoice("Entertainment");
        addChoice("Other");
        addChoice("All");
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

    void showPurchases(String category) {
        prln(category + ":");

        double totalCost = 0.0;
        List<BudgetItem> expenses = "All".equals(category) ? budgetService.getExpenses() : budgetService.getExpenses(category);

        if (expenses.isEmpty()) {
            prln("The purchase list is empty!");
        } else {
            for (BudgetItem expense : expenses) {
                totalCost += expense.getCostInDollars();
                prln(expense.asString());
            }

            prln("Total sum:" + BudgetItem.dollarsToString(totalCost));
        }
    }

    @Override
    protected void processChoice(int userChoice) {
        switch (userChoice) {
            case COMMAND_BACK:
                exitNow = true;
                break;

            case COMMAND_FOOD: {
                showPurchases("Food");
                break;
            }

            case COMMAND_CLOTHES: {
                showPurchases("Clothes");
                break;
            }

            case COMMAND_ENTERTAINMENT: {
                showPurchases("Entertainment");
                break;
            }

            case COMMAND_OTHER: {
                showPurchases("Other");
                break;
            }

            case COMMAND_ALL: {
                showPurchases("All");
                break;
            }

            default:
                handleBadChoice(userChoice);
                break;
        }
    }
}
