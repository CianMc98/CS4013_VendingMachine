import java.util.Scanner;
import java.io.IOException;

/**
 * A menu from the vending machine.
 */
public class CLI {
      private Scanner in;
      private static Coin[] coins = { new Coin(0.05, "5 cent"), new Coin(0.1, "10 cent"), new Coin(0.5, "50 cent"),
                  new Coin(1, "euro") };

      /**
       * Constructs a VendingMachineMenu object
       */
      public CLI() {
        in = new Scanner(System.in);
      }

      /**
       * Runs the vending machine system.
       *
       * @param manager the vending manager
       */
      public void run(VendingManager manager) throws IOException {
            boolean more = true;


            while (more) {
                  System.out.println("S)how products  I)nsert coin  B)uy  A)dd product  R)emove coins  Q)uit");

                  String command = in.nextLine().toUpperCase();

                  if (command.equals("S")) {
                        System.out.println(manager.showProducts());
                  } else if (command.equals("I")) // allows one coin be inserted at a time
                  {
                        manager.insertCoin((Coin) getChoice(coins));
                  } else if (command.equals("R")) {
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
                        String description = in.nextLine();
                        System.out.println("Price:");
                        double price = in.nextDouble();
                        System.out.println("Quantity:");
                        int quantity = in.nextInt();
                        in.nextLine(); // read the new-line character
                        manager.addItem(description, price, quantity);
                  } else if (command.equals("Q")) {
                        more = false;
                        // Write lineitems and money to their respective files
                        manager.writeAllDataToFiles();
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
