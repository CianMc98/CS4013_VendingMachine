/**
 * Coin
 */
public class Coin {
    private double amount;
    private String description;

    /**
     * Constructor for Coin Object
     * @param amount Amount that the coin is worth
     * @param description String representation of the coins worth
     */
    public Coin (double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description + "," + amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coin) {
            Coin other = (Coin)obj;
            return (this.description.equals(other.getDescription())
                    && this.amount == other.getAmount());
        } else {
            return false;
        }
    }
}