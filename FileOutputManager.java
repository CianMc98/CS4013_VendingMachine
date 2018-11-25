/**
 * FileOutputManager
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOutputManager {
    // Public Methods
    /**
     * Writes the LineItem ArrayList to the Stock.txt file
     * @param items ArrayList of LineItem type
     * @throws FileNotFoundException
     */
    public static void writeProductsToFile(ArrayList<LineItem> items) {
        File productFile = new File("Stock.txt");
        if (productFile.exists()) {
            try {
                FileWriter writer = new FileWriter(productFile);
                writer.write(parseProductListToString(items));
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Writes the CoinSet to the Money.txt file
     * @param coins CoinSet to write to file
     * @throws FileNotFoundException
     */
    public static void writeMoneyToFile(CoinSet coins) {
        File productFile = new File("Money.txt");
        if (productFile.exists()) {
            try {
                FileWriter writer = new FileWriter(productFile);
                writer.write(parseMoneyListToString(coins));
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Private Methods
    /* 
        Method to read the LineItem arrayList and return a String in the correct
        format so that it can later be written to the file
    */
    private static String parseProductListToString(ArrayList<LineItem> items){
        String out = "";
        for (LineItem item : items) {
            Product tmp = item.getProduct();
            out += tmp.getDescription() + ",";
            out += tmp.getPrice() + ",";
            out += item.getQuantity() + "\n";
        }
        return out;
    }

    /* 
        Method to read the CoinSet and return a String in the correct format 
        so that it can be later written to the file.
        Method counts the coins in the coinset and returns a string in the format
        Coin Description, value of coin, quantity in the machine
    */
    private static String parseMoneyListToString(CoinSet coins) {
        String s = "";
        ArrayList<Coin> cArray = coins.getCoinSet();
        // Count coins in the coin set
        Coin previousCoin = null;
        for (int i = 0; i < cArray.size(); i++) {
            Coin c = cArray.get(i);
            // Skip counting if it has already been counted
            if (!(c.equals(previousCoin))) {
                // Count number of coins of the same type
                int count = 0;
                for (int j = i; j < cArray.size(); j++) {
                    if (cArray.get(j).equals(c)) {
                        count++;
                    }
                }
                s += c.toString() + "," + count + "\n";
                previousCoin = c;
            } 
       }

       return s;
    }
}