import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.ArrayList;

public class GUI extends Application{

    Stage primaryStage;
    Button fail;
    VBox layout;
    Button AddProducts = new Button("Add Product(s)");
    Button RemoveCoins = new Button("Remove Coins");
    protected ArrayList < Operator > operatorList = FileInputManager.getOperatorList();
    protected Operator operator = new Operator();
  @Override
   public void start(Stage primary)
   {
     primaryStage = primary;
     try{
      VendingManager manager = new VendingManager();
		//scene for adding coins
		//Layout 1 - children laid out in vertical coloum
			Scene coinScene;
			Button fiftyCent = new Button("50 cent");
			Button twentyCent = new Button("20 cent");
			Button euro = new Button("1 euro");
			Button twoEuro = new Button("2 euro");
			Label label1 = new Label("Enter your coin");
      VBox layout1 = new VBox(20);
      layout1.getChildren().addAll(label1,fiftyCent,twentyCent,euro,twoEuro);
      coinScene = new Scene(layout1, 200, 200);

      primaryStage.setTitle("Vending Machine");
      fail = new Button("Click Me");
      Button EnterCoins = new Button("Enter Coins");
      Button ViewProducts = new Button("View Products");
      fail.setOnAction(e -> AlertBox.display("Error", "Incorrect code."));
      EnterCoins.setOnAction(e -> primaryStage.setScene(coinScene));
      EnterCoins.setOnAction(e -> primaryStage.setScene(coinScene));
      layout = new VBox();
      layout.getChildren().addAll(EnterCoins, ViewProducts);

      boolean end = false;
      while (!end) {
        if (operator.getType() == 0) {
          displayDefaultMenu(manager);
        } else {
          displayOperatorMenu(manager);
        }
      }
      Platform.exit();
    } catch(Exception e) {
      e.printStackTrace();
    }
    }

    private void displayOperatorMenu(VendingManager manager) {
        boolean more = true;
        int operatorLevel = operator.getType();
        try{
            if (operatorLevel == 1) {
                layout.getChildren().add(AddProducts);
                Scene scene = new Scene(layout, 300, 250);
                primaryStage.setScene(scene);
                primaryStage.show();
            } else if (operatorLevel == 2) {
                layout.getChildren().addAll(AddProducts, RemoveCoins);
                Scene scene = new Scene(layout, 300, 250);
                primaryStage.setScene(scene);
                primaryStage.show();
            }

        while (more) {

                // Write lineitems and money to their respective files
                manager.writeAllDataToFiles();

            }
        }
      catch(Exception e){
        e.printStackTrace();
      }}

    private void displayDefaultMenu(VendingManager manager) {
        boolean more = true;
        Scene scene1 = new Scene(layout, 300, 250);
        try {
          primaryStage.setScene(scene1);
        primaryStage.show();

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
    }
