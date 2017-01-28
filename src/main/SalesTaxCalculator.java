import java.util.ArrayList;
import java.text.DecimalFormat;

public class SalesTaxCalculator {

	private ArrayList<SaleItem> items = new ArrayList<SaleItem>();
	private ArrayList<String> reciept = new ArrayList<String>();
	private ArrayList<String> excemptItems = new ArrayList<String>();
	private ArrayList<String> skipWords = new ArrayList<String>();

	public SalesTaxCalculator(ArrayList<String> cart){
		loadExcemptItem();
		loadSkipWords();

		for(String item : cart) {
			loadItem(item);
		}
	}

	public SalesTaxCalculator calculate() {
		for(SaleItem item : items) {
			reciept.add( displayItemLine(item) );
		}
		reciept.add( displayTaxLine() );
		reciept.add( displayTotalLine() );

		return this;
	}

	public ArrayList<String> getReciept() {
		return reciept;
	}

	private void loadItem(String itemString) {
		String[] item = itemString.split(" ");

		String name      = parseName(item);
		double rate      = parseRate(item);
		int quantity     = parseQuantity(item);
		boolean excempt  = parseExcempt(item);
		boolean imported = parseImported(item);

		items.add( new SaleItem(name, rate, quantity, excempt, imported) );
	}

	private int parseQuantity(String[] item) {
		return Integer.parseInt(item[0]);
	}

	private String parseName(String[] item) {
		ArrayList<String> name = new ArrayList<String>();
		for(int i=1; i < item.length; i++) {
			if(namePriceDelimiter().equals(item[i])) {
				break;
			}
			if(!skipWords.contains(item[i])) {
				name.add(item[i]);
			}
		}
		return String.join(" ", name);
	}

	private boolean parseImported(String[] item) {
		boolean imported = false;
		for(String word : item) {
			if ("imported".equals(word)) {
				imported = true;
				break;
			}
		}
		return imported;
	}

	private double parseRate(String[] item) {
		return Double.parseDouble( item[lastIndex(item)] );

	}

	private int lastIndex(String[] ary) {
		return ary.length - 1;
	}

	private boolean parseExcempt(String[] item) {
		String[] name = parseName(item).split(" ");
		boolean excempt = false;
		for(String word : name) {
			if(isExcempted(word)) {
				excempt = true;
				break;
			}
		}
		return excempt;
	}

	private boolean isExcempted(String word) {
		return excemptItems.contains(word) || excemptItems.contains(singularOf(word));
	}

	private String singularOf(String word) {
		String[] letters = word.split("");
		if(letters[lastIndex(letters)].equals("s")) {
			letters[lastIndex(letters)] = "";
		}
		return String.join("", letters);
	}

	private void loadSkipWords() {
		skipWords.add("imported");
	}

	private void loadExcemptItem() {
		excemptItems.add("book");
		excemptItems.add("pill");
		excemptItems.add("chocolate");
	}

	private String namePriceDelimiter() {
		return "at";
	}

	private String displayItemLine(SaleItem item) {
		return quantityDisplay(item) + importedDisplay(item) + nameDisplay(item) + itemTotalDisplay(item);
	}

	private String quantityDisplay(SaleItem item) {
		return "" + item.getQuantity() + " ";
	}

	private String importedDisplay(SaleItem item) {
		String display = "";
		if (item.getImported()) {
			display = "imported ";
		}
		return display;
	}

	private String itemTotalDisplay(SaleItem item) {
		return displayAmount(item.total());
	}

	private String nameDisplay(SaleItem item) {
		return item.getName() + ": ";
	}

	private String displayTaxLine() {
		double tax = 0.0;
		for(SaleItem item : items) {
			tax = tax + item.tax();
		}
		return "Sales Taxes: " + displayAmount(tax);
	}

	private String displayTotalLine() {
		double total = 0.0;
		for(SaleItem item : items) {
			total = total + item.total();
		}
		return "Total: " + displayAmount(total);
	}

	private String displayAmount(double amount) {
		return new DecimalFormat("0.00").format(amount);
	}
}
