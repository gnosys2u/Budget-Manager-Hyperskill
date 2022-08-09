package budget;

import business.BudgetService;
import presentation.FirstMenu;

import static utils.Util.*;

public class Main {
    public static void main(String[] args) {
        setLogAll(true);
        BudgetService budgetService = new BudgetService();
        FirstMenu firstMenu = new FirstMenu(budgetService);
        firstMenu.interact();
        prln("Bye!");
    }
}
