package presentation;

import business.BudgetItem;
import business.BudgetService;

import static utils.Util.*;

public class FirstMenu extends ConsoleMenu {
    static final int COMMAND_EXIT = 0;
    static final int COMMAND_ADD_INCOME = 1;
    static final int COMMAND_ADD_PURCHASE = 2;
    static final int COMMAND_SHOW_PURCHASES = 3;
    static final int COMMAND_BALANCE = 4;
    static final int COMMAND_SAVE = 5;
    static final int COMMAND_LOAD = 6;
    static final int COMMAND_ANALYZE = 7;

    public FirstMenu(BudgetService budgetService) {
        super(budgetService);
        setTitle("Choose your action:");
        choices.add("Exit");
        choices.add("Add income");
        choices.add("Add purchase");
        choices.add("Show list of purchases");
        choices.add("Balance");
        choices.add("Save");
        choices.add("Load");
        choices.add("Analyze (Sort)");
    }

    @Override
    protected void processChoice(int userChoice) {
        switch (userChoice) {
            case COMMAND_EXIT:
                exitNow = true;
                break;

            case COMMAND_ADD_INCOME: {
                prln("Enter income:");
                double income = Double.parseDouble(scanLine());
                budgetService.addIncome(income);
                break;
            }

            case COMMAND_ADD_PURCHASE: {
                PurchaseMenu purchaseMenu = new PurchaseMenu(budgetService);
                purchaseMenu.interact();
                break;
            }

            case COMMAND_SHOW_PURCHASES: {
                if (budgetService.getExpenses().size() != 0) {
                    ListMenu listMenu = new ListMenu(budgetService);
                    listMenu.interact();
                } else {
                    prln("The purchase list is empty!");
                }
                break;
            }

            case COMMAND_BALANCE: {
                double balance = budgetService.getBalance();
                prln("Balance: " + BudgetItem.dollarsToString(balance));
                break;
            }

            case COMMAND_SAVE: {
                budgetService.saveLedger();
                prln("Purchases were saved!");
                break;
            }

            case COMMAND_LOAD: {
                budgetService.loadLedger();
                prln("Purchases were loaded!");
                break;
            }

            case COMMAND_ANALYZE: {
                AnalyzeMenu analyzeMenu = new AnalyzeMenu(budgetService);
                analyzeMenu.interact();
                break;
            }

            default:
                handleBadChoice(userChoice);
                break;
        }
    }
}
