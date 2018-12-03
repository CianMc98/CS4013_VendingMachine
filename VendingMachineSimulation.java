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
public class VendingMachineSimulation extends Application {

    public static void main(String[] args) {
        VendingManager manager = new VendingManager();
        int choice = Menu.chooseMenuType();
        if (choice == 1) {
            CLI cliMenu = new CLI();
            cliMenu.run(manager);
        } else if (choice == 2) {
          GUI guiMenu = new GUI();
          launch(args);
          guiMenu.run(manager);
        }
    }
}
