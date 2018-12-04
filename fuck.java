
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GUI extends Application {

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
  			back.setOnAction(e -> primary.setScene(scene));
  Button buy = new Button("Buy");
  LineItem[] lineItems = manager.getProductTypes();
  buy.setOnAction(e -> {manager.buyItem(lineItems[index]);
                        primary.setScene(scene);});
  Label name = new Label(productNames.get(index));
  Label nameIndicator = new Label("    Name: ");
  Label priceIndicator = new Label("    Price: $");
  Label price = new Label(Double.toString(productPrices.get(index)));
  box.getChildren().clear();
  box.getChildren().addAll(nameIndicator, name, priceIndicator, price, back, buy);
 }
