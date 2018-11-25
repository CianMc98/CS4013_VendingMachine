import java.util.ArrayList;

/**
 * A vending machine.
 */
public class VendingMachine {
    private ArrayList<LineItem> items;
    public CoinSet machineCoins;
    public CoinSet currentCoins;

    /**
     * Constructs a VendingMachine object.
     */
    public VendingMachine() {
        items = FileInputManager.getProductList();
        machineCoins = FileInputManager.getCoinSet();
        currentCoins = new CoinSet();
    }
    // ADD REMAINING CODE HERE

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
            if (currentCoins.getTotal() >= p.getProduct().getPrice()) {
                for (Coin coin : currentCoins.getCoinSet()) {
                    machineCoins.addCoin(coin);
                }
                currentCoins.clearSet();
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
}
