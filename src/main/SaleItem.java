import java.text.DecimalFormat;

public class SaleItem {
	String name;
	double baseRate;
	int quantity;
	boolean imported;
	boolean excempt;

	public SaleItem(String _name, double _rate, int _quantity, boolean _excempt, boolean _imported) {
		name 	   = _name;
		baseRate = _rate;
		quantity = _quantity;
		excempt  = _excempt;
		imported = _imported;
	}

	public String getName() {
		return name;
	}

	public double getBaseRate() {
		return baseRate;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean getImported() {
		return imported;
	}

	public boolean getExcempt() {
		return excempt;
	}

	public double tax() {
		double tax = balance() * percentTax() / 100;
		return roundToNextNickel(tax);
	}

	public double total() {
		return balance() + tax();
	}

	private double centsToDollars(int cents) {
		return (double) cents / 100.0;
	}

	private double balance() {
		return quantity * baseRate;
	}

	private double roundToNextNickel(double amount) {
		int cents = (int) (amount * 100);
		int padding = cents % 5;

		if (padding > 0) {
			cents = cents + 5 - padding;
		}
		return centsToDollars(cents);
	}

	private int percentTax() {
		int percentTax = 0;
		if(!excempt) {
			percentTax = percentTax + 10;
		}
		if(imported) {
			percentTax = percentTax + 5;
		}
		return percentTax;
	}

}

