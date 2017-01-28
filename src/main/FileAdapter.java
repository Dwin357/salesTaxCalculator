import java.io.*;
import java.util.ArrayList;

class FileAdapter {

	private static ArrayList<String> input = new ArrayList<String>();
	private static ArrayList<String> output = new ArrayList<String>();

	public static void main(String[] args) {

		String filepath = args[0];
		String target = "steinmetzSalesTax.txt";

		if (args.length > 1) {
			target = args[1];
		}

		load(filepath);
		perorm();
		save(target);
	}

	private static void load(String filepath){
		try {
			File myFile = new File(filepath);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			while ( (line = reader.readLine()) != null) {
				input.add(line);
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static void save(String target) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(target));
			for( String line : output) {
				writer.write(line + "\n");
			}
			writer.close();

		} catch (IOException ex) {
			System.out.println("Error");
			ex.printStackTrace();
		}
	}

	private static void perorm() {
		SalesTaxCalculator calc = new SalesTaxCalculator(input);
		output = calc.calculate().getReciept();
	}
}
