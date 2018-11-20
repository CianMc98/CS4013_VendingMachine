import java.util.ArrayList;

/**
   A vending machine.
*/
public class VendingMachine
{  
   private ArrayList<Product> products;
   public CoinSet coins;
   public CoinSet currentCoins;

   /**
      Constructs a VendingMachine object.
   */
   public VendingMachine()
   { 
      products = new ArrayList<Product>();
      coins = new CoinSet();
      currentCoins = new CoinSet();
   }
   //ADD REMAINING CODE HERE
    public Product[] getProductTypes() {
		
		Product[] arr = new Product[products.size()];
		return products.toArray(arr);
       
    }
       public double removeMoney() {
        double x = coins.getTotal();
        coins.clearSet();
        currentCoins.clearSet();
        return x;
    
    }

    public void addCoin(Coin c) {
        currentCoins.newCoin(c);
        coins.newCoin(c);
		System.out.println(coins.getTotal());
    }

    public void buyProduct(Product p) { 
        if(!(p.getQuantity() <= 0))
		{
		if(currentCoins.getTotal() >= p.getPrice()){
            for (Coin lol : currentCoins.getSet())
			{
				coins.newCoin(lol);
			}
            currentCoins.clearSet();
        }
		
		else{
			throw new VendingException("Insufficient coins");
		}
   
    } else {
		throw new VendingException("Product not in stock");
	}
	}

    public void addProduct(Product p) {
        if(!products.contains(p))
		{
		products.add(p);
		}else{
			System.out.println("already exists");
		}
    }

}
