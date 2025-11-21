import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;

public class VendingMachineApp extends Application
{
    private VendingMachine machine = new VendingMachine();
    private final ObservableList<Snack> oList = machine.toObservableList();
    public void start(Stage mainStage)
    {
        // Create a new border pane
        BorderPane root = new BorderPane();
        root.setStyle("-fx-font-size: 15;");

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene mainScene = new Scene(root, 500,500);
        mainStage.setTitle("Vending Machine App");
        mainStage.setScene(mainScene);
        
        // Set up the main layout
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        root.setCenter(mainLayout);
        mainLayout.setSpacing(20);
        
        // Create the table
        TableView<Snack> table = new TableView();
        mainLayout.getChildren().add(table);
        table.setItems(oList);
        
        // Snack Name Column
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(120);
        table.getColumns().add(nameCol);
        
        // Calories Column
        TableColumn calCol = new TableColumn("Calories");
        calCol.setCellValueFactory(new PropertyValueFactory("cals"));
        calCol.setMinWidth(90);
        table.getColumns().add(calCol);
        
        // Sugars Column
        TableColumn sugarCol = new TableColumn("Sugars (g)");
        sugarCol.setCellValueFactory(new PropertyValueFactory("sugar"));
        sugarCol.setMinWidth(90);
        table.getColumns().add(sugarCol);
        
        // Cost Column
        TableColumn costCol = new TableColumn("Cost");
        costCol.setCellValueFactory(new PropertyValueFactory("cost"));
        costCol.setMinWidth(90);
        table.getColumns().add(costCol);
        
        // remove extra columns/rows
        table.setMaxWidth(390);
        table.setMaxHeight(490);
        
        // Button row
        HBox buttonRow = new HBox();
        buttonRow.setSpacing(10);
        buttonRow.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(buttonRow);

        // Button to buy a product from the machine
        Button buyButton = new Button("Buy Product");
        buyButton.setOnAction((event->{
            Snack bought = machine.buy_input();
            Alert alert = new Alert(AlertType.INFORMATION);
            if (bought != null) {
                alert.setTitle("Product Dispensed");
                alert.setHeaderText("Product Dispensed");
                alert.setContentText("Product dispensed: " + bought.toString());
                oList.remove(bought);
            }
            else
            {
                alert.setTitle("Product Not Dispensed");
                alert.setHeaderText("Product Not Dispensed");
                alert.setContentText("Product is not in this machine");
            }
            alert.showAndWait();
            table.refresh();
        }));
        
        // Button to discard an item from user inventory to add back into the machine
        Button discardButton = new Button("Discard Product from Inventory");
        discardButton.setOnAction((event->{
            Snack discarded = machine.discard_input();
            Alert alert = new Alert(AlertType.INFORMATION);
            if (discarded != null) {
                alert.setTitle("Product Discarded");
                alert.setHeaderText("Product Discarded");
                alert.setContentText("Product Discarded: " + discarded.toString());
                oList.add(discarded);
            }
            else
            {
                alert.setTitle("Product Not Discarded");
                alert.setHeaderText("Product Not Discarded");
                alert.setContentText("Product is not in your current inventory");
            }
            alert.showAndWait();
            table.refresh();
        }));
        
        buttonRow.getChildren().addAll(buyButton,discardButton);
        
        // Set the menu for functionality
        MenuBar topMenu = new MenuBar();
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        
        // Menu item - All Items in Inventory
        MenuItem inventItem = new MenuItem("Your Products");
        inventItem.setOnAction((e->
        {
            String allItems = machine.allYourItems();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Your Products Purchased");
            alert.setHeaderText("Your Products Purchased");
            alert.setContentText(allItems.toString());
            alert.showAndWait();
        }));
        
        // Menu item - Checkout and Reset
        MenuItem resetItem = new MenuItem("Checkout/Reset");
        resetItem.setOnAction((e->
        {
            String allItems = machine.allYourItems();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Checkout Information");
            alert.setHeaderText("Checkout Information");
            alert.setContentText("Your Products Purchased: " + allItems.toString());
            alert.showAndWait();
            oList.clear();
            machine.reset();
            Iterator<Snack> iter = machine.iterator();
            while (iter.hasNext())
            {
                Snack next = iter.next();
                oList.add(next);
            }
            table.refresh();
        }));
        
        // Menu item - Quit
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            mainStage.close();
            System.exit(0);
        }));
        fileMenu.getItems().addAll(inventItem, resetItem, quitItem);
        
        mainStage.show();
    }
}
