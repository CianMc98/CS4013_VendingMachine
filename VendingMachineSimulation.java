import java.util.*;
import java.io.*;
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

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation{

    public static void main(String[] args) {
      int choice = Menu.chooseMenuType();
                if (choice == 1) {
                    VendingManager manager = new VendingManager();
                    CLI cliMenu = new CLI();
                    cliMenu.run(manager);
                } else if (choice == 2) {
      Application.launch(GUI.class, args);
    }
    }

    // @Override
    //  public void start(Stage primaryStage) {
    //
    //        int choice = Menu.chooseMenuType();
    //            if (choice == 1) {
    //                VendingManager manager = new VendingManager();
    //                CLI cliMenu = new CLI();
    //                cliMenu.run(manager);
    //            } else if (choice == 2) {
    //                    GUI guiMenu = new GUI();
    //                     guiMenu.start(primaryStage);
    //            }
    //  }
}
