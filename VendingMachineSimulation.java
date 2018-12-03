import java.util.*;
import java.io.*;

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation {

    public static void main(String[] args) {
        VendingManager manager = new VendingManager();
        int choice = Menu.chooseMenuType();
        if (choice == 1) {
            CLI cliMenu = new CLI();
            cliMenu.run(manager);


        } else if (choice == 2) {
            /*  GUI guiMenu= new GUI();
            guiMenu.run(manager); */
        }
    }
}