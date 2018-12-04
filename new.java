
 public void setDisplay(int index, HBox box, ArrayList < String > productNames, ArrayList < Double > productPrices) {
   try {
  Button back = new Button("Back");
  			back.setOnAction(e -> primary.setScene(scene1));
  Button buy = new Button("Buy");
  buy.setOnAction(e -> {  if (productPrices.get(index)<manager.getCurrentBalance()) {
                         AlertBox.display("Bought", "You have successfully purchased the product");
                         primary.setScene(scene1);
                         manager.buyItem(index);
                        } else {
                          AlertBox.display("Error", "Insuficcient Money");
                        }
                        });
  Label name = new Label(productNames.get(index));
  Label nameIndicator = new Label("    Name: ");
  Label priceIndicator = new Label("    Price: $");
  Label price = new Label(Double.toString(productPrices.get(index)));
  box.getChildren().clear();
  box.getChildren().addAll(nameIndicator, name, priceIndicator, price, back, buy);
} catch (Exception e) {
  e.printStackTrace();
}
 }
