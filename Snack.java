
public class Snack
{
    private String name;
    private int cals, sugar;
    private double cost;
    // Constructor
    public Snack(String name, int cals, int sugar, double cost)
    {
        this.name = name;
        this.cals = cals;
        this.sugar = sugar;
        this.cost = cost;
    }
    // Name of snack
    public String getName()
    {
        return name;
    }
    // Calories
    public int getCals()
    {
        return cals;
    }
    // Sugar
    public int getSugar()
    {
        return sugar;
    }
    // Cost of snack
    public double getCost()
    {
        return cost;
    }
    // toString method
    public String toString()
    {
        return "Name: " + name + "/Calories: " + cals + "/Sugars: " + sugar + "/Cost: " + cost;
    }
}
