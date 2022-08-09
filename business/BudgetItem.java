package business;

public class BudgetItem {

    private final static double ROUNDING_SMIDGE = 0.001;
    private String description;
    private String category;
    private double costInDollars;


    public BudgetItem(String description, String category, double costInDollars) {
        this.description = description;
        this.category = category;
        this.costInDollars = costInDollars;
    }

    public String getDescription() {
        return description;
    }

    public double getCostInDollars() {
        return costInDollars;
    }

    public String getCategory() {
        return category;
    }

    public static String dollarsToString(double dollarsIn) {
        Double doubleCents = (dollarsIn * 100.0) + ROUNDING_SMIDGE;
        int costInCents =  doubleCents.intValue();
        int dollars = costInCents / 100;
        int cents = costInCents % 100;
        return String.format("$%d.%02d", dollars, cents);
    }

    public String asString() {
        return description + " " + dollarsToString(costInDollars);
    }
}
