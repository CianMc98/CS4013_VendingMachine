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

public class GUI extends Menu {

    Stage primaryStage;
    Button fail;

    public GUI() {
      start(primaryStage);
    }

    public void start(Stage primaryStage) {
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
      StackPane layout = new StackPane();
      layout.getChildren().addAll(EnterCoins, ViewProducts);
      Scene scene = new Scene(layout, 300, 250);
      primaryStage.setScene(scene);
    }

    public void run(VendingManager manager) {
      boolean end = false;
      while (!end) {
        if (operator.getType() == 0) {
          displayDefaultMenu(manager);
        } else {
          displayOperatorMenu(manager);
        }
      }
    }

    private void displayOperatorMenu(VendingManager manager) {
        boolean more = true;
        int operatorLevel = operator.getType();

            if (operatorLevel == 1) {
                System.out.println("S)how products  I)nsert coin  B)uy  A)dd Products  Q)uit");
                primaryStage.show();
            } else if (operatorLevel == 2) {
                System.out.println("S)how products  I)nsert coin  B)uy  A)dd Products R)emove Coins  Q)uit");
                primaryStage.show();
            }

        while (more) {

                // Write lineitems and money to their respective files
                manager.writeAllDataToFiles();

            }
        }

    private void displayDefaultMenu(VendingManager manager) {
        boolean more = true;
        primaryStage.show();


        while (more) {

                more = false;

                //more = false;
                //end = true;
                // Write lineitems and money to their respective files
                manager.writeAllDataToFiles();

            }
        }
    }
