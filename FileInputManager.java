import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * FileInputManager
 */
public class FileInputManager {

    private static String readFromFile(String fileName) throws IOException{
        File file = new File(fileName);
        Scanner reader = null;
        String in = "";

        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                in += reader.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            file.createNewFile();
        } finally {
            reader.close();
        }

        return in;
    }

    public static ArrayList<LineItem> parseProductsToList() {
        ArrayList<LineItem> items = new ArrayList<>();
        
        try {
            String products = readFromFile("Stock.txt");

            // Split product string so each line can be converted to a LineItem
            String[] lineItemStrings = products.split("\n");

            for (String s : lineItemStrings) {
                // Split into seperate parts and create object in list
                String[] tmp = s.split(",");
                Product p = new Product(tmp[0], Double.parseDouble(tmp[1]));
                items.add(new LineItem(p, Integer.parseInt(tmp[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static CoinSet parseMoneyToList() {
        CoinSet coinSet = new CoinSet();
        
        try {
            String moneyString = readFromFile("Money.txt");

            // Split product string so each line can be converted to a LineItem
            String[] coinStrings = moneyString.split("\n");

            for (String s : coinStrings) {
                // Create coins and add as by amount based on quantity
                String[] tmp = s.split(",");
                Coin c = new Coin(Double.parseDouble(tmp[1]), tmp[0]);

                for (int i = 0; i < Integer.parseInt(tmp[2]); i++) {
                    coinSet.addCoin(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coinSet;
    }
}