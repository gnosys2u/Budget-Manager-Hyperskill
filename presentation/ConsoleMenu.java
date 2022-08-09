package presentation;

import business.BudgetService;

import java.util.ArrayList;

import static utils.Util.*;

public class ConsoleMenu {
    protected ArrayList<String> choices;
    protected boolean exitNow;
    protected BudgetService budgetService;
    protected String title;
    protected int lowestChoice;

    ConsoleMenu(BudgetService budgetService) {
        this.exitNow = false;
        this.budgetService = budgetService;
        this.choices = new ArrayList<>();
        this.title = null;
        this.lowestChoice = 0;
    }

    protected void processChoice(int userChoice) {
        exitNow = true;
    }

    public int interact() {
        int userChoice = -1;
        while (!exitNow) {
            showChoices();
            String userLine = scanLine();
            userChoice = Integer.parseInt(userLine);
            prln("");
            if (userChoice >= lowestChoice && userChoice < (choices.size() + lowestChoice)) {
                processChoice(userChoice);
            }
            prln("");
        }
        return userChoice;
    }

    public void showChoices() {
        if (title != null) {
            prln(title);
        }

        for (int i = 1; i < choices.size(); ++i) {
            prln(i + ") " + choices.get(i));
        }
        prln("0) " + choices.get(0));
    }

    public void handleBadChoice(int userChoice) {
        prln("Incorrect option. Please try again");
        showChoices();
    }

    protected void addChoice(String choice) {
        choices.add(choice);
    }

    protected void setTitle(String title) {
        this.title = title;
    }
}
