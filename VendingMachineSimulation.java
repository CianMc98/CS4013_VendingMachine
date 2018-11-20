import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation extends Application
{ 
	private static boolean fail = true;
	Button button;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   public static void main(String[] args)throws IOException{
	   System.out.println("Would you like to use A) GUI or B) CLI");
	   String input = br.readLine();
	   input = input.toUpperCase();
	   
	   while (fail)
	   {
	   if (input.equals("A"))
	   {
	   
         launch(args);
		 fail = false;
	   }
		 else if (input.equals("B"))
		 {
	        startCLI();
		 }
		 else { System.out.println("Incorrect input try again");
		 input = br.readLine();
		 input.toUpperCase();
		 }
	   }
		 
		 
}
@Override
public void start(Stage primaryStage) throws IOException
{
 window = primaryStage;

        //Button 1
        Label label1 = new Label("Vending Macbine");
		Label label3 = new Label("You picked operator");
		Button operator = new Button("Operator");
        operator.setOnAction(e -> window.setScene(scene3));
        Button customer = new Button("Customer");
        customer.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1,customer,operator);
        scene1 = new Scene(layout1, 300, 350);
		
		//scene3
		StackPane layout3 = new StackPane();
		layout3.getChildren().add(label3);
		scene3 = new Scene(layout3,200,300);

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(label1);
        scene2 = new Scene(layout2, 600, 300);
		

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("VendingMachine");
        window.show();
}
		 public static void startCLI()
		 {
	try{
				{ 
					VendingMachine machine = new VendingMachine();
					VendingMachineMenu menu = new VendingMachineMenu();
					menu.run(machine);
					fail = false;
				}
		}
	catch (Exception e)
			 {
				 
			 }
			 
		 }
		 
		 
		 
		 

}