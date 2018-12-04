import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * FileInputManager
 */
public class FileInputManager {

    /*
     * Method to read the contents of a file with the filename being passed as a
     * method parameter If the file is not found then it is created
     */
    private static String readFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        String in = "";

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                 in += reader.nextLine() + "\n";
            }

            reader.close();
        } catch (FileNotFoundException e) {
            file.createNewFile();
        }

        return in;
    }

    public static ArrayList < LineItem > getProductList() {
        ArrayList < LineItem > items = new ArrayList < > ();

        try {
            String products = readFromFile("Stock.txt");

            // if file is empty return the array list early
            if (products.length() > 0) {
                // Split product string so each line can be converted to a LineItem
                String[] lineItemStrings = products.split("\n");

                for (String s: lineItemStrings) {
                    // Split into seperate parts and create object in list
                    String[] tmp = s.split(",");
                    Product p = new Product(tmp[0], Double.parseDouble(tmp[1]));
                    items.add(new LineItem(p, Integer.parseInt(tmp[2])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static CoinSet getCoinSet() {
        CoinSet coinSet = new CoinSet();

        try {
            String moneyString = readFromFile("Balance.txt");

            if (moneyString.length() > 0) {
                // Split product string so each line can be converted to a LineItem
                String[] coinStrings = moneyString.split("\n");

                for (String s: coinStrings) {
                    // Create coins and add as by amount based on quantity
                    String[] tmp = s.split(",");
                    Coin c = new Coin(Double.parseDouble(tmp[1]), tmp[0]);

                    for (int i = 0; i < Integer.parseInt(tmp[2]); i++) {
                        coinSet.addCoin(c);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coinSet;
    }

    public static ArrayList <Operator> getOperatorList() {
        ArrayList <Operator> operatorList  = new ArrayList<Operator>();
           
        try{
            String operatorfile = readFromFile("Operators.txt");
            
            if(operatorfile.length() > 0) {
                String [] operatorStrings = operatorfile.split("\n");

                for(String o: operatorStrings) {
                    String [] tmp = o.split(",");
                    operatorList.add(new Operator(tmp[0], Integer.parseInt(tmp[1])));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return operatorList;
    }                
          
    public static Coin[] getCoinTypes() {
        Coin[] coinTypes = null;

        try {
            String coinFileString = readFromFile("Money.txt");

            if (coinFileString.length() > 0) {
                String[] coinStrings = coinFileString.split("\n");
                coinTypes = new Coin[coinStrings.length];

                for (int i = 0; i < coinStrings.length; i++) {
                    String[] coinInfo = coinStrings[i].split(",");
                    coinTypes[i] = new Coin(Double.parseDouble(coinInfo[1]), coinInfo[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return coinTypes;
    }
}