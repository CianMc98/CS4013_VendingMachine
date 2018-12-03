import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class GUI extends Application {

    Scene productScene;
		Scene coinScene;

   public static void main(String[] args) {
      launch(args);
   }

    @Override
    public void start(Stage primaryStage) {
      primaryStage.setTitle("Vending Machine");
      //fail = new Button("Click Me");
			Button EnterCoins = new Button("Enter Coins");
			Button ViewProducts = new Button("View Products");
      //fail.setOnAction(e -> AlertBox.display("Error", "Incorrect code."));
			EnterCoins.setOnAction(e -> primaryStage.setScene(coinScene));
			EnterCoins.setOnAction(e -> primaryStage.setScene(coinScene));
      StackPane layout = new StackPane();
      layout.getChildren().addAll(EnterCoins, ViewProducts);
      Scene scene = new Scene(layout, 300, 250);
      primaryStage.setScene(scene);
      primaryStage.show();
    }
		//scene for adding coins
		//Layout 1 - children laid out in vertical column

			Button fiftyCent = new Button("50 cent");
			Button twentyCent = new Button("20 cent");
			Button euro = new Button("1 euro");
			Button twoEuro = new Button("2 euro");
			Label label1 = new Label("Enter your coin");
      VBox layout1 = new VBox(20);
      layout1.getChildren().addAll(label1,fiftyCent,twentyCent,euro,twoEuro);
      coinScene = new Scene(layout1, 200, 200);

			ChoiceBox<Product> choiceBox = new ChoiceBox<>();
			choiceBox.getItems().addAll(machine.getProductTypes());
      productScene = new Scene(layout1, 200, 200);

}
