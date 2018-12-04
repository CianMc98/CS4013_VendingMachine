import java.util.ArrayList;
public class VendingManager {
	VendingMachine machine;

	public VendingManager() {
		machine = new VendingMachine();
	}

	public void insertCoin(Coin coin) {
		machine.addCoin(coin);
	}

	public String showProducts() {
		String y = "";
		for (LineItem p : getProductTypes())
			y += p.getProduct().toString() + "\n";
		return y;

	}

	public ArrayList<String> showProductNames() {
		ArrayList<String> y = new ArrayList<>();
		for (LineItem p : getProductTypes())
			y.add(p.getProduct().getDescription());
		return y;
	}

	public ArrayList<Double> showProductPrices() {
		ArrayList<Double> y = new ArrayList<>();
		for (LineItem p : getProductTypes())
			y.add(p.getProduct().getPrice());
		return y;
	}

	public LineItem[] getProductTypes() {

		LineItem[] arr = new LineItem[machine.getItemList().size()];
		return machine.getItemList().toArray(arr);
	}

	public ArrayList<LineItem> getProductList() {

		return machine.getItemList();
	}

	public void removeMoney() {
		System.out.println("Coins removed, Remaining Balance: " + machine.removeMoney());

	}

	public void buyItem(LineItem l) throws VendingException {
		machine.buyProduct(l);
	}

	public void buyItem(int index) throws VendingException {
		machine.buyProduct(getProductList().get(index));
	}

	public void addItem(String description, double price, int quantity) {
		machine.addProduct(new LineItem(new Product(description, price), quantity));
	}

	public double getCurrentBalance() {
		return machine.getCurrentCoinSet().getTotal();
	}
	public void writeAllDataToFiles() {
		FileOutputManager.writeMoneyToFile(machine.getCoinSet());
		FileOutputManager.writeProductsToFile(machine.getItemList());
	}
}
