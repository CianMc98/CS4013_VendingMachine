import java.util.ArrayList;

/**
 * A set of coins.
 */
public class CoinSet {
    private ArrayList<Coin> set;

    /**
     * Constructs a CoinSet object.
     */
    public CoinSet() {
        set = new ArrayList<Coin>();
    }
    // ADD REMAINING CODE HERE

    public void addCoin(Coin c) {
        set.add(c);
    }

    public void clearSet() {
        set.clear();
    }

    public double getTotal() {
        double total = 0;
        for (Coin c : set) {
            total += c.getAmount();
        }

        return total;
    }

    public ArrayList<Coin> getCoinSet() {
        return set;
    }

}