import java.util.ArrayList;
import java.util.Scanner;
/**
   A set of coins.
*/
public class CoinSet
{  
   private ArrayList<Coin> set;

   /**
      Constructs a CoinSet object.
   */
   public CoinSet()
   {  
      set = new ArrayList<Coin>();
   }
   //ADD REMAINING CODE  HERE

    public void newCoin(Coin c) {
       set.add(c);
    }

    public void clearSet() {
        set.clear();
    }

    public double getTotal() {
        double total = 0;
        
        for(int i=0; i< set.size();i++) {
            total += set.get(i).getValue();
        }
        return total;
    }
	public ArrayList<Coin> getSet()
	{
		return set;
	}

}