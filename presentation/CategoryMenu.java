package presentation;

import business.BudgetService;

import static utils.Util.*;

public class CategoryMenu extends ConsoleMenu {
    static final int COMMAND_FOOD = 1;
    static final int COMMAND_CLOTHES = 2;
    static final int COMMAND_ENTERTAINMENT = 3;
    static final int COMMAND_OTHER = 4;

    private String chosenCategory;
    public CategoryMenu(BudgetService budgetService) {
        super(budgetService);

        setTitle("Choose the type of purchase");
        lowestChoice = 1;

        addChoice("Food");
        addChoice("Clothes");
        addChoice("Entertainment");
        addChoice("Other");
        chosenCategory = "";
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
            case COMMAND_FOOD:
            case COMMAND_CLOTHES:
            case COMMAND_ENTERTAINMENT:
            case COMMAND_OTHER:
                exitNow = true;
                chosenCategory = choices.get(userChoice - 1);
                break;

            default:
                handleBadChoice(userChoice);
                break;
        }
    }

    public String getChosenCategory() {
        return chosenCategory;
    }
}
