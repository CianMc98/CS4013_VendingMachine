import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A vending machine.
 */
public class VendingMachine {
    private ArrayList<LineItem> items;
    private CoinSet machineCoins;
    private CoinSet currentCoins;

    /**
     * Constructs a VendingMachine object.
     */
    public VendingMachine() {
        items = FileInputManager.getProductList();
        machineCoins = FileInputManager.getCoinSet();
        currentCoins = new CoinSet();
    }

    public double removeMoney() {
        double x = machineCoins.getTotal();
        machineCoins.clearSet();
        currentCoins.clearSet();
        return x;

    }

    public void addCoin(Coin c) {
        currentCoins.addCoin(c);
    }

    public void buyProduct(LineItem p) {
        if (!(p.getQuantity() <= 0)) {
            // If enough money has been entered into the machine
            if (currentCoins.getTotal() >= p.getProduct().getPrice()) {
                // Get list of coins to make up price of product and add to machine coins
                ArrayList<Coin> coins = convertValueToCoinList(p.getProduct().getPrice());
                for (Coin c : coins) {
                    machineCoins.addCoin(c);
                }

                coins = convertValueToCoinList(currentCoins.getTotal() - p.getProduct().getPrice());
                currentCoins.clearSet();
                for (Coin c : coins) {
                    currentCoins.addCoin(c);
                }
                // Get remaining total of the inserted coins and set inserted coins to the new list
                p.setQuantity(p.getQuantity() - 1);
            }

            else {
                throw new VendingException("Insufficient coins");
            }

        } else {
            throw new VendingException("Product not in stock");
        }
    }

    /**
     * Adds a product to the vening machine. If the product already exists then the
     * quantity of the existing product is incremented.
     * 
     * @param p product to add to the machine
     */
    public void addProduct(LineItem newItem) {
        // Check if product already exists
        boolean found = false;
        for (LineItem item : items) {
            if (newItem.getProduct().equals(item.getProduct())) {
                // If already there increase quantity
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                found = true;
                break;
            }
        }
        // Otherwise add to the arraylist
        if (!found) {
            items.add(newItem);
        }
    }

    public ArrayList<LineItem> getItemList() {
        return items;
    }

    public CoinSet getCoinSet() {
        return machineCoins;
    }

    public CoinSet getCurrentCoinSet() {
        return currentCoins;
    }

    // Converts a double value to an arrayist of coins to be used when buying products
    private ArrayList<Coin> convertValueToCoinList(double value) {
        BigDecimal v = new BigDecimal(Double.toString(value));

        ArrayList<Coin> coins = new ArrayList<Coin>(Arrays.asList(FileInputManager.getCoinTypes()));
        ArrayList<Coin> outCoins = new ArrayList<Coin>();
        Coin currentLargestCoin = getMostValuableCoinFromList(coins);
        
        while (v.compareTo(BigDecimal.valueOf(0)) != 0) {

            if (v.compareTo(BigDecimal.valueOf(currentLargestCoin.getAmount())) >= 0) {
                outCoins.add(currentLargestCoin);
                v = v.subtract(new BigDecimal(Double.toString(currentLargestCoin.getAmount())));
            } else {
                coins.remove(currentLargestCoin);
                if (coins.size() != 0) {
                    currentLargestCoin = getMostValuableCoinFromList(coins);
                }
            }
        }

        return outCoins;
    }

    // Returns the coin with the hightest value from an arraylist
    private Coin getMostValuableCoinFromList(ArrayList<Coin> set) {
        Coin valuableCoin = set.get(0);
        for (Coin c : set) {
            if (c.getAmount() > valuableCoin.getAmount() ) {
                valuableCoin = c;
            }
        }
        return valuableCoin;
    }
}
