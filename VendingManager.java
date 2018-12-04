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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GUI extends Application {

 Stage primary;
 Button fail;
 Scene operatorScene, scene,scene1;
 VBox MainLayout;
 Button AddProducts = new Button("Add Product(s)");
 Button RemoveCoins = new Button("Remove Coins");
 Scene viewProducts;
 Scene coinScene;
 protected VendingManager manager = new VendingManager();
 protected ArrayList < Operator > operatorList = FileInputManager.getOperatorList();
 protected Operator operator = new Operator();
 private Coin fifty = new Coin(0.5,".5e");
 private Coin twenty = new Coin(0.2,".2e");
 private Coin oneE = new Coin(1.0,"1e");
 private Coin twoE = new Coin(2.0,"2e");

  @Override
   public void start(Stage primaryStage)
   {
     primary = primaryStage;
     try{
      VendingManager manager = new VendingManager();
		//scene for adding coins
		//Layout 1 - children laid out in vertical coloum
			Scene coinScene;
			Button getCurrentTotal = new Button("getTotal");
			getCurrentTotal.setOnAction(e -> System.out.println(manager.getCurrentBalance()));
			Button fiftyCent = new Button("50 cent");
			fiftyCent.setOnAction(e -> manager.insertCoin(fifty));
			Button twentyCent = new Button("20 cent");
			twentyCent.setOnAction(e -> manager.insertCoin(twenty));
			Button euro = new Button("1 euro");
			euro.setOnAction(e -> manager.insertCoin(oneE));
			Button twoEuro = new Button("2 euro");
			twoEuro.setOnAction(e -> manager.insertCoin(twoE));
			Button back = new Button("Back");
			Button back1 = new Button("Back");
			Button back2 = new Button("Back");
			back.setOnAction(e -> primary.setScene(scene));
			back1.setOnAction(e -> primary.setScene(scene));
			back2.setOnAction(e -> primary.setScene(scene));
			Label label1 = new Label("Enter your coin");
      VBox coinLayout = new VBox(20);
      coinLayout.getChildren().addAll(label1,fiftyCent,twentyCent,euro,twoEuro,back1,getCurrentTotal);
      coinScene = new Scene(coinLayout, 300, 300);
   primary.setTitle("Vending Machine");
   fail = new Button("Click Me");
   Button EnterCoins = new Button("Insert Coins");
   Button viewProduct = new Button("View Products");
   Button changeOperator = new Button("Change Operator");
   changeOperator.setOnAction(e -> primary.setScene(operatorScene));
   Button quit = new Button("Quit");
   quit.setOnAction(e -> primary.close());
   fail.setOnAction(e -> AlertBox.display("Error", "Incorrect code."));
   EnterCoins.setOnAction(e -> primary.setScene(coinScene));
   viewProduct.setOnAction(e -> ViewProducts(primary));
   MainLayout = new VBox();
   MainLayout.getChildren().addAll(EnterCoins, viewProduct, changeOperator, quit);

   //Change operator
   GridPane operatorPane = new GridPane();
   operatorPane.setPadding(new Insets(10, 10, 10, 10)); //sets 10 pixel padding around it
   operatorPane.setVgap(8); // vertical 8 pixel gap
   operatorPane.setHgap(10);
   Label operatorCode = new Label("Enter Operator Code: ");
   GridPane.setConstraints(operatorCode, 0, 0); //place label in first column of first row
   GridPane.setConstraints(back, 1, 2);
   TextField input = new TextField();
   input.setPromptText("012786");
   GridPane.setConstraints(input, 1, 0); //place input in second column of first row
   Button login = new Button("login");
   GridPane.setConstraints(login, 1, 1);
   operatorPane.getChildren().addAll(operatorCode, input, login, back);
   operatorScene = new Scene(operatorPane, 300, 200);

   login.setOnAction(e -> {
    if (checkPass(input.getText()) == true) {
     displayOperatorMenu(manager);

    } else {
     AlertBox.display("Error", "Incorrect code.");
     //System.out.println("hey");
    }
   });


   displayOperatorMenu(manager);
   Platform.exit();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }


 public void ViewProducts(Stage primaryStage) {
  try {
   ArrayList < String > productNames = manager.showProductNames();
   ArrayList < Double > productPrices = manager.showProductPrices();
   VBox box = new VBox();
   ComboBox < String > cbo = new ComboBox < > ();

   setDisplay(0, box, productNames, productPrices);
   BorderPane pane = new BorderPane();
   BorderPane paneForComboBox = new BorderPane();
   paneForComboBox.setLeft(new Label("Select a country: "));
   paneForComboBox.setCenter(cbo);
   pane.setTop(paneForComboBox);
   cbo.setPrefWidth(400);

   ObservableList < String > names = FXCollections.observableArrayList(productNames);
   cbo.getItems().addAll(names);
   pane.setCenter(box);

   cbo.setOnAction(e -> setDisplay(names.indexOf(cbo.getValue()), box, productNames, productPrices));

   viewProducts = new Scene(pane, 450, 170);
   primaryStage.setScene(viewProducts);
   primaryStage.show();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 private void displayOperatorMenu(VendingManager manager) {
  try {
       scene = new Scene(MainLayout, 300, 250);
   boolean more = true;
   int operatorLevel = operator.getType();
   if (operatorLevel == 1) {
    MainLayout.getChildren().add(AddProducts);
    primary.setScene(scene);
    primary.show();
   } else if (operatorLevel == 2) {
    MainLayout.getChildren().addAll(AddProducts, RemoveCoins);
    primary.setScene(scene);
    primary.show();
   } else if (operatorLevel == 0) {
    primary.setScene(scene);
    primary.show();
    while (more) {
     more = false;
     manager.writeAllDataToFiles();
    }
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  manager.writeAllDataToFiles();
 }
 public void setDisplay(int index, VBox box, ArrayList < String > productNames, ArrayList < Double > productPrices) {
  Label name = new Label(productNames.get(index));
  Label price = new Label(Double.toString(productPrices.get(index)));
  box.getChildren().addAll(name, price);
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
}

