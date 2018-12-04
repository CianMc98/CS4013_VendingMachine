import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * A menu from the vending machine.
 */
public class CLI {
    private Scanner in ;
    boolean end = false; // Ends the run function
    private static Coin[] coins;
    private ArrayList<Operator> operatorList;
    private Operator operator;

    /**
     * Constructs a VendingMachineMenu object
     */
    public CLI() { 
        in = new Scanner(System.in);
        coins = FileInputManager.getCoinTypes();
        operatorList = FileInputManager.getOperatorList();
        operator = new Operator();
    }


    /**
     * Runs the vending machine system.
     *
     * @param manager the vending manager
     */
    public void run(VendingManager manager) {
        while (!end) {
            if (operator.getType() == 0) {
                displayDefaultMenu(manager);
            } else {
                displayOperatorMenu(manager);
            }
        }
    }
    private void displayOperatorMenu(VendingManager manager) {
        boolean more = true;
        int operatorLevel = operator.getType();


        while (more) {
            if (operatorLevel == 1) {
                System.out.println("S)how products  I)nsert coin  B)uy  D)isplay balance A)dd Products  Q)uit");
            } else if (operatorLevel == 2) {
                System.out.println("S)how products  I)nsert coin  B)uy  D)isplay balance A)dd Products R)emove Coins  Q)uit");
            }

            String command = in .nextLine().toUpperCase();

            if (command.equals("S")) {
                System.out.println(manager.showProducts());
            } else if (command.equals("I")) // allows one coin be inserted at a time
            {
                manager.insertCoin((Coin) getChoice(coins));
            } else if (command.equals("R") && operatorLevel == 2) {
                manager.removeMoney();
            } else if (command.equals("B")) {
                try {
                    LineItem p = (LineItem) getChoice(manager.getProductTypes());
                    manager.buyItem(p);
                } catch (VendingException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (command.equals("A")) {
                System.out.println("Description:");
                String description = in .nextLine();
                System.out.println("Price:");
                double price = in .nextDouble();
                System.out.println("Quantity:");
                int quantity = in .nextInt(); in .nextLine(); // read the new-line character
                manager.addItem(description, price, quantity);
            } else if (command.equals("D")) {
                System.out.println("Balance : " + manager.getCurrentBalance());
            } else if (command.equals("Q")) {
                more = false;
                end = true;
                // Write lineitems and money to their respective files
                manager.writeAllDataToFiles();

            }
        }
    }

    private void displayDefaultMenu(VendingManager manager) {
        boolean more = true;


        while (more) {
            System.out.println("S)how products  I)nsert coin  B)uy  C)hange Operator  D)isplay balance Q)uit");

            String command = in .nextLine().toUpperCase();

            if (command.equals("S")) {
                System.out.println(manager.showProducts());
            } else if (command.equals("I")) // allows one coin be inserted at a time
            {
                manager.insertCoin((Coin) getChoice(coins));
            } else if (command.equals("B")) {
                try {
                    LineItem p = (LineItem) getChoice(manager.getProductTypes());
                    manager.buyItem(p);
                } catch (VendingException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (command.equals("C")) {
                boolean isChanged = false;
                System.out.println("Enter Operator code");
                Scanner op = new Scanner(System.in);
                String passcode = op.nextLine();
                // Check to see if input passcode equals an operator passcode in the list
                for (Operator o: operatorList) {
                    if (o.getPasscode().equals(passcode)) {
                        operator = o;
                        isChanged = true;
                    }
                }

                if(isChanged) {
                    System.out.println("Logging in as level " + operator.getType() + " operator");
                } else {
                    System.out.println("Incorrect password, log in failed!");
                }
                // Reloads the CLI but does not quit the program
                more = false;

            } else if (command.equals("D")) {
                System.out.println("Balance : " + manager.getCurrentBalance());
            } else if (command.equals("Q")) {
                more = false;
                end = true;
                // Write lineitems and money to their respective files
                manager.writeAllDataToFiles();

            }
        }
    }

    private Object getChoice(Object[] choices) {
        while (true) {
            char c = 'A';
            for (Object choice: choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in .nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.length)
                return choices[n];
        }
    }
}