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

    Stage primary;
    Button fail;
    VBox MainLayout;
    Button AddProducts = new Button("Add Product(s)");
    Button RemoveCoins = new Button("Remove Coins");
    protected ArrayList < Operator > operatorList = FileInputManager.getOperatorList();
    protected Operator operator = new Operator();

  @Override
   public void start(Stage primaryStage)
   {
     primary = primaryStage;
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
      VBox coinLayout = new VBox(20);
      coinLayout.getChildren().addAll(label1,fiftyCent,twentyCent,euro,twoEuro);
      coinScene = new Scene(coinLayout, 200, 200);

      primary.setTitle("Vending Machine");
      fail = new Button("Click Me");
      Button EnterCoins = new Button("Insert Coins");
      Button ViewProducts = new Button("View Products");
      Button changeOperator = new Button("Change Operator");
      Button quit = new Button("Quit");
      fail.setOnAction(e -> AlertBox.display("Error", "Incorrect code."));
      EnterCoins.setOnAction(e -> primary.setScene(coinScene));
      EnterCoins.setOnAction(e -> primary.setScene(coinScene));
     MainLayout = new VBox();
     MainLayout.getChildren().addAll(EnterCoins, ViewProducts, changeOperator, quit);

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
               MainLayout.getChildren().add(AddProducts);
                Scene scene = new Scene(MainLayout, 300, 250);
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
        }
      catch(Exception e){
        e.printStackTrace();
      }}

    private void displayDefaultMenu(VendingManager manager) {
        boolean more = true;
        Scene scene1 = new Scene(MainLayout, 300, 250);
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
    }
