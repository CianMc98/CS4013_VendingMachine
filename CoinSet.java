import java.math.BigDecimal;
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

    // Gets the total amount of money in the coinset as a double, uses bigdecimal for correct precision
    public double getTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Coin c : set) {
            total = total.add(new BigDecimal(Double.toString(c.getAmount())));
        }

        return total.doubleValue();
    }

    public ArrayList<Coin> getCoinSet() {
        return set;
    }

}