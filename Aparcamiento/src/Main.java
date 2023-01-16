import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.lang.StringBuffer;

public class Main {

	public static ArrayList<Car> cars = new ArrayList<Car>();
	public static File car_list = new File("car_list.txt");
	public static File exits = new File("exits.txt");
	public static File entries = new File("entries.txt");

	public static void main(String[] args) {

		checkCarsFile();

		String[] options = { "Create new file", "Show vehicles list", "Show entries", "Show exits",
				"Create new vehicle", "New entry", "New exit", "Close the program" };

		int select = JOptionPane.showOptionDialog(null, "Welcome to Parking Manager, please choose an option",
				"Parking Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
				options[0]);
		boolean exit = false;
		while (exit != true) {
			switch (select) {
			case 0:
				try {
					CreateNewFile();
				} catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "Closing the program");
				exit = true;
			}
		}
	}

	public static void checkCarsFile() {
		// Checking if a car list file is created, if not, we create one with 4 examples
		if (car_list.exists() == false) {
			try {
				Car car_1 = new Car("Peugeot", "Red", "5698HDC");
				cars.add(car_1);
				Car car_2 = new Car("Audi", "White", "8471KLB");
				cars.add(car_2);
				Car car_3 = new Car("BMW", "Black", "3458DFC");
				cars.add(car_3);
				Car car_4 = new Car("Honda", "Gray", "8214LOP");
				cars.add(car_4);

				car_list.createNewFile();
				FileWriter fw = new FileWriter(car_list);
				fw.write(car_1.toString() + "\n");
				fw.write(car_2.toString() + "\n");
				fw.write(car_3.toString() + "\n");
				fw.write(car_4.toString() + "\n");
				fw.flush();
				fw.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
			}
			// If the file is already created, we read the file creating one car object for
			// every line we read and then we add it to the ArrayList
		} else {
			try {
				String brand;
				String color;
				String plate;

				FileReader fr = new FileReader(car_list);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				StringBuffer sb;
				int index_1;
				int index_2;
				while ((line = br.readLine()) != null) {
					// First we remove the car id of the string line
					sb = new StringBuffer(line);
					index_1 = line.indexOf('[');
					index_2 = line.indexOf(',');
					line = String.valueOf(sb.replace(index_1 - 1, index_2 + 1, ""));

					// The next step is taking the value of the brand
					index_1 = line.indexOf('=');
					index_2 = line.indexOf(',');
					brand = sb.substring(index_1 + 1, index_2);
					// And then we remove it from the string line
					index_1 = line.indexOf('b');
					line = String.valueOf(sb.replace(index_1 - 1, index_2 + 1, ""));

					// The next value we will take is the color of the car, and we will do it the
					// same way
					index_1 = line.indexOf('=');
					index_2 = line.indexOf(',');
					color = sb.substring(index_1 + 1, index_2);
					index_1 = line.indexOf('c');
					line = String.valueOf(sb.replace(index_1 - 1, index_2 + 1, ""));

					// And the last value we take is the car plate
					index_1 = line.indexOf('=');
					index_2 = line.indexOf(']');
					plate = sb.substring(index_1 + 1, index_2);
	
					Car car = new Car(brand, color, plate);
					cars.add(car);
				}
				br.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void CreateNewFile() throws IOException {
		String [] options = {"Create list file", "Create entries file", "Create exits file", "Exit"};
		int select = JOptionPane.showOptionDialog(null, "Choose the file type",
				"Parking Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
				options[0]);
		int count = 1;
		boolean exit = false;
		File file;
		switch(select) {
			case 0:
				while(exit != true) {
					file = new File("car_list_" + String.valueOf(count) + ".txt");
					if(file.exists()) {
						count++;
					} else {
						file.createNewFile();
						break;
					}
				}
				break;
			case 1:
				while(exit != true) {
					file = new File("entries_" + String.valueOf(count) + ".txt");
					if(file.exists()) {
						count++;
					} else {
						file.createNewFile();
						break;
					}
				}
				break;
			case 2:
				while(exit != true) {
					file = new File("exits_" + String.valueOf(count) + ".txt");
					if(file.exists()) {
						count++;
					} else {
						file.createNewFile();
						break;
					}
				}
				break;
			case 3:
			return;	
		}
	}
}
