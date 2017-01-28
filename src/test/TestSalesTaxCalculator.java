import static org.junit.Assert.*;
import junit.framework.*;
import java.util.ArrayList;

public class TestSalesTaxCalculator extends TestCase {
	protected SalesTaxCalculator subject;

	public void testScenarioOne() {
		subject = new SalesTaxCalculator(inputOne());
		ArrayList<String> expected = outputOne();

		subject.calculate();

		assertEquals(expected, subject.getReciept());
	}

	public void testScenarioTwo() {
		subject = new SalesTaxCalculator(inputTwo());
		ArrayList<String> expected = outputTwo();

		subject.calculate();

		assertEquals(expected, subject.getReciept());
	}

	public void testScenarioThree() {
		subject = new SalesTaxCalculator(inputThree());
		ArrayList<String> expected = outputThree();

		subject.calculate();

		assertEquals(expected, subject.getReciept());
	}

	private ArrayList<String> inputTwo() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("1 imported box of chocolates at 10.00");
		input.add("1 imported bottle of perfume at 47.50");
		return input;
	}

	private ArrayList<String> outputTwo() {
		ArrayList<String> output = new ArrayList<String>();
		output.add("1 imported box of chocolates: 10.50");
		output.add("1 imported bottle of perfume: 54.65");
		output.add("Sales Taxes: 7.65");
		output.add("Total: 65.15");
		return output;
	}

	private ArrayList<String> inputThree() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("1 imported bottle of perfume at 27.99");
		input.add("1 bottle of perfume at 18.99");
		input.add("1 packet of headache pills at 9.75");
		input.add("1 box of imported chocolates at 11.25");
		return input;
	}

	private ArrayList<String> outputThree() {
		ArrayList<String> output = new ArrayList<String>();
		output.add("1 imported bottle of perfume: 32.19");
		output.add("1 bottle of perfume: 20.89");
		output.add("1 packet of headache pills: 9.75");
		output.add("1 imported box of chocolates: 11.85");
		output.add("Sales Taxes: 6.70");
		output.add("Total: 74.68");
		return output;
	}

	private ArrayList<String> inputOne() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("1 book at 12.49");
		input.add("1 music CD at 14.99");
		input.add("1 chocolate bar at 0.85");
		return input;
	}

	private ArrayList<String> outputOne() {
		ArrayList<String> output = new ArrayList<String>();
		output.add("1 book: 12.49");
		output.add("1 music CD: 16.49");
		output.add("1 chocolate bar: 0.85");
		output.add("Sales Taxes: 1.50");
		output.add("Total: 29.83");
		return output;
	}
}
