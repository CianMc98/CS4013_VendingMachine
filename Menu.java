import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public abstract class Menu {


    protected ArrayList < Operator > operatorList = FileInputManager.getOperatorList();
    protected Operator operator = new Operator();

    
        public static int chooseMenuType() {
            System.out.println("Would you like to use A) GUI or B) CLI");
            Scanner sc = new Scanner(System.in);

            String choice = sc.nextLine().toUpperCase();

            return choice == "A" ? 0 : 1;

        }

        abstract void run(VendingManager manager);
    }