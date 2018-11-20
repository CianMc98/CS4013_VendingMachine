/**
 * LineItem
 */
public class LineItem {
    private Product product;
    private int quantity;
    
    /**
     * Constructs a LineItem object
     * @param product Product contained in LineItem
     * @param quantity Quantity of product
     */
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters
    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    // Setters
    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Methods
    /**
     * Returns the product name and the quantity
     */
    @Override
    public String toString() {
        return product.toString() + ", " + quantity;
    }
}