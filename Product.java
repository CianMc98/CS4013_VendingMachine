/**
 * Product
 */
public class Product {
    private String description;
    private double price; 

    Product (String description, double price) {
        this.description = description;
        this.price = price;
    }

    // Accessors
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns string containing product info
     * @return Product info
     */
    @Override
    public String toString() {
        return description + ", Euro: " + price;
    }

    /**
     * Check for equality between two Product objects
     * @return <code>true if products are equal else <code>false
     */
    @Override
    public boolean equals(Object obj) {
        //Check if self
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Product)) {
            return false;
        }

        Product temp = (Product)obj;
        return ((description.equals(temp.getDescription())) && price == temp.getPrice());
    }
}