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
public class VendingMachineSimulation 
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
		 
		 fail = false;
	   }
		 else if (input.equals("B"))
		 {
	        VendingManager machine = new VendingManager();
					VendingMachineMenu menu = new VendingMachineMenu();
					menu.run(machine);
			fail = false;
		 }
		 else { System.out.println("Incorrect input try again");
		 input = br.readLine();
		 input.toUpperCase();
		 }
	   }
		 
		 
}
		 
		 

}