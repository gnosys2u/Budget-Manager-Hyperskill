package presentation;

import business.BudgetItem;
import business.BudgetService;

import static utils.Util.*;
import static utils.Util.prln;

public class PurchaseMenu extends ConsoleMenu {
    static final int COMMAND_FOOD = 1;
    static final int COMMAND_CLOTHES = 2;
    static final int COMMAND_ENTERTAINMENT = 3;
    static final int COMMAND_OTHER = 4;
    static final int COMMAND_BACK = 5;

    public PurchaseMenu(BudgetService budgetService) {
        super(budgetService);

        setTitle("Choose the type of purchase");
        lowestChoice = 1;

        addChoice("Food");
        addChoice("Clothes");
        addChoice("Entertainment");
        addChoice("Other");
        addChoice("Back");
    }

    private void addPurchase(String category) {
        prln("Enter purchase name:");
        String description = scanLine();
        prln("Enter its price:");
        Double price = Double.parseDouble(scanLine());
        budgetService.addExpense(description, category, price);
        prln("Purchase was added!");
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

    @Override
    protected void processChoice(int userChoice) {
        switch (userChoice) {
            case COMMAND_BACK:
                exitNow = true;
                break;

            case COMMAND_FOOD: {
                addPurchase("Food");
                break;
            }

            case COMMAND_CLOTHES: {
                addPurchase("Clothes");
                break;
            }

            case COMMAND_ENTERTAINMENT: {
                addPurchase("Entertainment");
                break;
            }

            case COMMAND_OTHER: {
                addPurchase("Other");
                break;
            }

            default:
                handleBadChoice(userChoice);
                break;
        }
    }

}
