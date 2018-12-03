import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public abstract class Menu {


    protected ArrayList < Operator > operatorList = FileInputManager.getOperatorList();
    protected Operator operator = new Operator();


        public static int chooseMenuType() {
        boolean chosen = false;
        int x = 0;
          while (!chosen) {
            System.out.println("Would you like to use A) GUI or B) CLI");
            Scanner sc = new Scanner(System.in);

            String choice = sc.nextLine().toUpperCase();

            if (choice.matches("A")) {
              x = 2;
              chosen = true;
            } else if (choice.matches("B")) {
              x = 1;
              chosen = true;
            }
          }
          return x;
        }

        abstract void run(VendingManager manager);
    }
