import javafx.collections.*;
import java.util.*;
import javafx.scene.control.*;
public class VendingMachine extends LinkedList<Snack>
{
    public LinkedList<Snack> yourItems = new LinkedList<>();
    // Constructor
    public VendingMachine()
    {
        add(new Snack("Potato Chips", 150, 1, 2.5));
        add(new Snack("Pretzels", 110, 1, 2.5));
        add(new Snack("Popcorn (Butter)", 120, 0, 2.5));
        add(new Snack("Granola Bar", 190, 12, 1));
        add(new Snack("Chcolate Bar", 210, 24, 1));
        add(new Snack("Gummy Bears", 140, 20, 1.5));
    }
    // Reset
    public void reset()
    {
        clear();
        add(new Snack("Potato Chips", 150, 1, 2.5));
        add(new Snack("Pretzels", 110, 1, 2.5));
        add(new Snack("Popcorn (Butter)", 120, 0, 2.5));
        add(new Snack("Granola Bar", 190, 12, 1));
        add(new Snack("Chcolate Bar", 210, 24, 1));
        add(new Snack("Gummy Bears", 140, 20, 1.5));
    }
    // Buy snack given name input
    public Snack buy_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter Snack Name");
        dialog.setContentText("Enter name of a snack to buy: ");
        dialog.setTitle("Enter Snack Name");
        Optional<String> optional = dialog.showAndWait();
        String name = optional.get();
        return buySnack(name);
    }
    // Helper method to buy snack with given name input
    public Snack buySnack(String name)
    {
        Snack removed = null;
        if (isEmpty())
        {
            return removed;
        }
        Iterator<Snack> iter = iterator();
        while (iter.hasNext())
        {
            Snack current = iter.next();
            if (current.getName().equals(name))
            {
                removed = current;
                break;
            }
        }
        if (removed != null) {
            remove(removed);
            yourItems.add(removed);
        }
        return removed;
    }
    // Discard snack from user inventory given input
    public Snack discard_input()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter Snack Name");
        dialog.setContentText("Enter name of a snack to remove from inventory: ");
        dialog.setTitle("Enter Snack Name");
        Optional<String> optional = dialog.showAndWait();
        String name = optional.get();
        return discardSnack(name);
    }
    // Helper method to discard a snack from the user inventory based on given input
    public Snack discardSnack(String name)
    {
        Snack added = null;
        if (yourItems.isEmpty())
        {
            return added;
        }
        Iterator<Snack> iter = yourItems.iterator();
        while (iter.hasNext())
        {
            Snack current = iter.next();
            if (current.getName().equals(name))
            {
                added = current;
                break;
            }
        }
        if (added != null) {
            yourItems.remove(added);
            add(added);
        }
        return added;
    }
    // List all items in your inventory
    public String allYourItems()
    {
        if (yourItems.isEmpty())
        {
            return "You have no items purchased.";
        }
        double totalCost = 0;
        StringBuilder sb = new StringBuilder();
        Iterator<Snack> iter = yourItems.iterator();
        while (iter.hasNext())
        {
            Snack next = iter.next();
            totalCost += next.getCost();
            sb.append(next.toString() + ";");
        }
        sb.append("Total Cost: " + totalCost);
        return sb.toString();
    }
    // Convert to an observable list for use with the TableView component
    public ObservableList<Snack> toObservableList()
    {
        ObservableList<Snack> list = FXCollections.observableArrayList();
        Iterator<Snack> iter = iterator();
        while (iter.hasNext())
        {
            Snack next = iter.next();
            list.add(next);
        }
        return list;
    }
    // Main method to test data structure operations
    public static void main(String[] args)
    {
        VendingMachine machine = new VendingMachine();
        System.out.println(machine.buySnack("Oranges"));
        System.out.println(machine.buySnack("Potato Chips"));
        System.out.println(machine.buySnack("Potato Chips"));
        System.out.println(machine.allYourItems());
        System.out.println(machine.discardSnack("Potato Chips"));
        System.out.println(machine.allYourItems());
    }
}
