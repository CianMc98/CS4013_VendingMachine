import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.application.*;
/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation{

    public static void main(String[] args) {
      boolean chosen = false;
      int x = 0;
        while (!chosen) {
          System.out.println("Would you like to use A) GUI or B) CLI or C) Quit");
          Scanner sc = new Scanner(System.in);

          String choice = sc.nextLine().toUpperCase();

          if (choice.matches("A")) {
            Application.launch(GUI.class, args);
            chosen = true;
          } else if (choice.matches("B")) {
            VendingManager manager = new VendingManager();
            CLI cliMenu = new CLI();
            cliMenu.run(manager);
            chosen = true;
          } else if(choice.matches("C")) {
            chosen = true;
          }
        }
    }
}
