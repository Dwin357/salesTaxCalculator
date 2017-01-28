import static org.junit.Assert.*;
import junit.framework.*;

public class TestSaleItem extends TestCase {

	protected SaleItem subject;

	public void testAssignmentAndGetters() {
		subject = new SaleItem("book", 12.49, 1, true, false);

		assertEquals("book", subject.getName());
		assertEquals(12.49, subject.getBaseRate());
		assertEquals(1, subject.getQuantity());
		assertTrue(subject.getExcempt());
		assertFalse(subject.getImported());
	}

	public void testTotal_ExcemptUnimported() {
		subject = new SaleItem("book", 12.49, 1, true, false);
		double expected = 12.49;

		assertEquals(expected, subject.total());
	}

	public void testTotal_UnexcemptUnimported() {
		subject = new SaleItem("music CD", 14.99, 1, false, false);
		double expected = 16.49;

		assertEquals(expected, subject.total(), 0.001);
	}

	public void testTotal_ExcemptImported() {
		subject = new SaleItem("imported box of chocolates", 10.00, 1, true, true);
		double expected = 10.50;

		assertEquals(expected, subject.total());
	}

	public void testTotal_UnexcemptImported() {
		subject = new SaleItem("imported bottle of perfume", 47.50, 1, false, true);
		double expected = 54.65;

		assertEquals(expected, subject.total());
	}

	public void testTotal_MultileItems() {
		subject = new SaleItem("imported bottle of perfume", 47.50, 2, false, true);
		double expected = 109.25;

		assertEquals(expected, subject.total());
	}

	public void testDisplayTax() {
		subject = new SaleItem("imported bottle of perfume", 47.50, 2, false, true);
		double expected = 14.25;

		assertEquals(expected, subject.tax());
	}
}
