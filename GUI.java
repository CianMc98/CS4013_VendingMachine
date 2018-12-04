import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GUI extends Application {

 Stage primary;
 Button fail;
 Scene opScene, scene1, scene, coinScene, viewProducts, productScene;
 VBox MainLayout;
 Button AddProducts = new Button("Add Product(s)");
 Button RemoveCoins = new Button("Remove Coins");
 protected ArrayList < Operator > operatorList = FileInputManager.getOperatorList();
 protected VendingManager manager = new VendingManager();
 protected Operator operator = new Operator();
 private Coin fifty = new Coin(0.5, ".5e");
 private Coin ten = new Coin(0.1, ".1e");
 private Coin oneE = new Coin(1.0, "1e");
 private Coin fiveC = new Coin(0.05, "0.05e");
 //manager.insertCoin((Coin) getChoice(coins));


 @Override
 public void start(Stage primaryStage) {
  primary = primaryStage;
  try {
   //scene for adding coins
   //Layout 1 - children laid out in vertical coloum
   Button getCurrentTotal = new Button("Current Total");
   getCurrentTotal.setOnAction(e -> AlertBox.display("Success", getTotalCoins()));
   Button fiftyCent = new Button("50 cent");
   fiftyCent.setOnAction(e -> manager.insertCoin(fifty));
   Button tenCent = new Button("10 cent");
   tenCent.setOnAction(e -> manager.insertCoin(ten));
   Button euro = new Button("1 euro");
   euro.setOnAction(e -> manager.insertCoin(oneE));
   Button fiveCent = new Button("5 Cent");
   fiveCent.setOnAction(e -> manager.insertCoin(fiveC));
   Button back = new Button("Back");
   Button back1 = new Button("Back");
   Button backProducts = new Button("Back");
   back.setOnAction(e -> primary.setScene(scene1));
   back1.setOnAction(e -> primary.setScene(scene1));
   backProducts.setOnAction(e -> primary.setScene(scene1));

   Label label1 = new Label("Enter your coin");
   VBox coinLayout = new VBox(20);
   coinLayout.getChildren().addAll(label1, fiveCent, tenCent, fiftyCent, euro, back1, getCurrentTotal);
   coinScene = new Scene(coinLayout, 300, 300);

   primary.setTitle("Vending Machine");
   fail = new Button("Click Me");
   Button EnterCoins = new Button("Insert Coins");
   Button viewProducts = new Button("View Products");
   viewProducts.setOnAction(e -> ViewProducts(primary));
   Button changeOperator = new Button("Change Operator");
   changeOperator.setOnAction(e -> primary.setScene(opScene));
   Button quit = new Button("Quit");
   quit.setOnAction(e -> primary.close());
   fail.setOnAction(e -> AlertBox.display("Error", "Incorrect code."));
   EnterCoins.setOnAction(e -> primary.setScene(coinScene));
   MainLayout = new VBox();
   MainLayout.getChildren().addAll(EnterCoins, viewProducts, changeOperator, quit);

   RemoveCoins.setOnAction(e -> manager.removeMoney());



   AddProducts.setOnAction(e -> primary.setScene(productScene));

   GridPane productsPane = new GridPane();
   productsPane.setPadding(new Insets(10, 10, 10, 10)); //sets 10 pixel padding around it
   productsPane.setVgap(8); // vertical 8 pixel gap
   productsPane.setHgap(10);
   Label descriptionLabel = new Label("Description: ");
   GridPane.setConstraints(descriptionLabel, 0, 0);
   TextField descriptionTF = new TextField();
   GridPane.setConstraints(descriptionTF, 1, 0);

   Label priceLabel = new Label("Price: ");
   GridPane.setConstraints(priceLabel, 0, 1);
   TextField priceTF = new TextField();
   GridPane.setConstraints(priceTF, 1, 1);

   Label quantityLabel = new Label("Quantity: ");
   GridPane.setConstraints(quantityLabel, 0, 2);
   TextField quantityTF = new TextField();
   GridPane.setConstraints(quantityTF, 1, 2);

   Button enter = new Button("Enter");
   GridPane.setConstraints(enter, 2, 3);
   productsPane.getChildren().addAll(descriptionLabel, descriptionTF, priceLabel, priceTF,
    quantityLabel, quantityTF, backProducts, enter);
   productScene = new Scene(productsPane, 300, 200);
   enter.setOnAction(e -> displayOperatorMenu(manager));

   GridPane.setConstraints(backProducts, 1, 3);

   //Change operator
   GridPane operatorr = new GridPane();
   operatorr.setPadding(new Insets(10, 10, 10, 10)); //sets 10 pixel padding around it
   operatorr.setVgap(8); // vertical 8 pixel gap
   operatorr.setHgap(10);
   Label operatorCode = new Label("Enter Operator Code: ");
   GridPane.setConstraints(operatorCode, 0, 0); //place label in first column of first row
   GridPane.setConstraints(back, 1, 2);
   TextField input = new TextField(); 
   input.setPromptText("012786");
   GridPane.setConstraints(input, 1, 0); //place input in second column of first row
   Button login = new Button("login");
   GridPane.setConstraints(login, 1, 1);
   operatorr.getChildren().addAll(operatorCode, input, login, back);
   opScene = new Scene(operatorr, 300, 200);

   login.setOnAction(e -> {
    if (checkPass(input.getText()) == true) {
     displayOperatorMenu(manager);
     primary.setScene(scene1);

    } else {
     AlertBox.display("Error", "Incorrect code.");

    }
   });


   boolean end = false;
   while (!end) {
    if (operator.getType() == 0) {
     displayDefaultMenu(manager);
    } else {
     displayOperatorMenu(manager);
    }
   }
   Platform.exit();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 private void displayOperatorMenu(VendingManager manager) {
  boolean more = true;
  int operatorLevel = operator.getType();
  try {
   if (operatorLevel == 1) {
    MainLayout.getChildren().add(AddProducts);
    scene = new Scene(MainLayout, 300, 250);
    primary.setScene(scene);
    primary.show();
   } else if (operatorLevel == 2) {
    MainLayout.getChildren().addAll(AddProducts, RemoveCoins);
    Scene scene = new Scene(MainLayout, 300, 250);
    primary.setScene(scene);
    primary.show();
   }

   while (more) {

    // Write lineitems and money to their respective files
    manager.writeAllDataToFiles();

   }
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 private void displayDefaultMenu(VendingManager manager) {
  boolean more = true;
  scene1 = new Scene(MainLayout, 300, 250);
  try {
   primary.setScene(scene1);
   primary.show();

   while (more) {

    more = false;

    //more = false;
    //end = true;
    // Write lineitems and money to their respective files
    manager.writeAllDataToFiles();

   }
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
 private boolean checkPass(String xx) {
  String passcode = xx;
  boolean x = false;

  for (Operator o: operatorList) {
   if (o.getPasscode().equals(passcode)) {
    operator = o;
    x = true;
   }
  }
  return x;
 }
 private String getTotalCoins() {
  String xxx = Double.toString(manager.getCurrentBalance());
  return xxx;
 }
 public void ViewProducts(Stage primaryStage) {
  try {
   ArrayList < String > productNames = manager.showProductNames();
   ArrayList < Double > productPrices = manager.showProductPrices();
   HBox box = new HBox();
   ComboBox < String > cbo = new ComboBox < > ();

   BorderPane pane = new BorderPane();
   BorderPane paneForComboBox = new BorderPane();
   paneForComboBox.setLeft(new Label("Select a product: "));
   paneForComboBox.setCenter(cbo);
   pane.setTop(paneForComboBox);
   cbo.setPrefWidth(400);

   ObservableList < String > names = FXCollections.observableArrayList(productNames);
   cbo.getItems().addAll(names);
   pane.setCenter(box);

   cbo.setOnAction(e -> setDisplay(names.indexOf(cbo.getValue()), box, productNames, productPrices));

   viewProducts = new Scene(pane, 450, 100);
   primaryStage.setScene(viewProducts);
   primaryStage.show();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 public void setDisplay(int index, HBox box, ArrayList < String > productNames, ArrayList < Double > productPrices) {
  Button back = new Button("Back");
  			back.setOnAction(e -> primary.setScene(scene1));
  Button buy = new Button("Buy");
  LineItem[] lineItems = manager.getProductTypes();
  buy.setOnAction(e -> {  AlertBox.display("Bought", "You have successfully purchased the product");
                         primary.setScene(scene1);
                          manager.buyItem(lineItems[index]);});
  Label name = new Label(productNames.get(index));
  Label nameIndicator = new Label("    Name: ");
  Label priceIndicator = new Label("    Price: $");
  Label price = new Label(Double.toString(productPrices.get(index)));
  box.getChildren().clear();
  box.getChildren().addAll(nameIndicator, name, priceIndicator, price, back, buy);
 }

}
