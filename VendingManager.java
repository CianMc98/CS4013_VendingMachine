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

	public LineItem[] getProductTypes() {

		LineItem[] arr = new LineItem[machine.getItemList().size()];
		return machine.getItemList().toArray(arr);

	}

	public void removeMoney() {
		System.out.println("Removed: " + machine.removeMoney());

	}

	public void buyItem(LineItem l) throws VendingException {
		machine.buyProduct(l);
	}

	public void addItem(String description, double price, int quantity) {
		machine.addProduct(new LineItem(new Product(description, price), quantity));
	}

	public void writeAllDataToFiles() {
		FileOutputManager.writeMoneyToFile(machine.getCoinSet());
		FileOutputManager.writeProductsToFile(machine.getItemList());
	}
}