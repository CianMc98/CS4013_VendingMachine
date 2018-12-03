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

public class GUI extends Application {

    Stage window;
    Button fail;
	Scene coinScene;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Vending Machine");
        fail = new Button("Click Me");
		Button EnterCoins = new Button("Enter coins");

        fail.setOnAction(e -> AlertBox.display("Error", "Incorrect operator code."));
		EnterCoins.setOnAction(e -> window.setScene(coinScene));

        StackPane layout = new StackPane();
        layout.getChildren().add(EnterCoins);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
		
		//scene for adding coins
		//Layout 1 - children laid out in vertical column
		Button fiftyCent = new Button("50 cent");
		fiftyCent.setOnAction(e -> );
		Button twentyCent = new Button("20 cent");
		Button euro = new Button("1 euro");
		Button twoEuro = new Button("2 euro");
		Label label1 = new Label("Enter your coin");
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,fiftyCent,twentyCent,euro,twoEuro);
        coinScene = new Scene(layout1, 200, 200);
    }

}