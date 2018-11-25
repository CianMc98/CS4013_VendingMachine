import java.util.Scanner;
import java.io.IOException;

/**
 * A menu from the vending machine.
 */
public class VendingMachineMenu {
      private Scanner in;
      private static Coin[] coins = { new Coin(0.05, "5 cent"), new Coin(0.1, "10 cent"), new Coin(0.5, "50 cent"),
                  new Coin(1, "euro") };

      /**
       * Constructs a VendingMachineMenu object
       */
      public VendingMachineMenu() {
            in = new Scanner(System.in);
      }

      /**
       * Runs the vending machine system.
       * 
       * @param machine the vending machine
       */
      public void run(VendingManager machine) throws IOException {
            boolean more = true;

            while (more) {
                  System.out.println("S)how products  I)nsert coin  B)uy  A)dd product  R)emove coins  Q)uit");

                  String command = in.nextLine().toUpperCase();

                  if (command.equals("S")) {
                        System.out.println(machine.showProducts());
                  } else if (command.equals("I")) // allows one coin be inserted at a time
                  {
                        machine.insertCoin((Coin) getChoice(coins));
                  } else if (command.equals("R")) {
                        machine.removeMoney();
                  } else if (command.equals("B")) {
                        try {
                              LineItem p = (LineItem) getChoice(machine.getProductTypes());
                              machine.buyItem(p);
                        } catch (VendingException ex) {
                              System.out.println(ex.getMessage());
                        }
                  } else if (command.equals("A")) {
                        System.out.println("Description:");
                        String description = in.nextLine();
                        System.out.println("Price:");
                        double price = in.nextDouble();
                        System.out.println("Quantity:");
                        int quantity = in.nextInt();
                        in.nextLine(); // read the new-line character
                        machine.addItem(description, price, quantity);
                  } else if (command.equals("Q")) {
                        more = false;
                        // Write lineitems and money to their respective files
                        machine.writeAllDataToFiles();
                  }
            }
      }

      private Object getChoice(Object[] choices) {
            while (true) {
                  char c = 'A';
                  for (Object choice : choices) {
                        System.out.println(c + ") " + choice);
                        c++;
                  }
                  String input = in.nextLine();
                  int n = input.toUpperCase().charAt(0) - 'A';
                  if (0 <= n && n < choices.length)
                        return choices[n];
            }
      }
}