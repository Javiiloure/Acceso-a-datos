import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {

	public static ArrayList<Car> cars = new ArrayList<Car>();
	public static File car_list = new File("car_list_1.txt");
	public static File exits = new File("exits_1.txt");
	public static File entries = new File("entries_1.txt");

	public static void main(String[] args) {

		checkCarsFile();
		checkEntriesFile();
		checkExitsFile();

		String[] options = { "Create new file", "Show vehicles list", "Show entries", "Show exits",
				"Create new vehicle", "New entry", "New exit", "Close the program" };

		int select;
		boolean exit = false;
		while (exit != true) {
			select = JOptionPane.showOptionDialog(null, "Welcome to Parking Manager, please choose an option",
					"Parking Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
					options[0]);
			switch (select) {
			case 0:
				try {
					CreateNewFile();
				} catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 1:
				try {
					Car.showVehicles();
				} catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 2:
				try {
				Entry.showEntries();
				} catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 3:
				try {
					Exit.showExits();
				} catch(IOException e) {
					JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 4:
				Car.createVehicle(car_list);
				break;
			case 5:
				Entry.newEntry(entries);
				break;
			case 6:
				Exit.newExit(exits);
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
				Car car_2 = new Car("Audi", "White", "8471KLB");
				Car car_3 = new Car("BMW", "Black", "3458DFC");
				Car car_4 = new Car("Honda", "Gray", "8214LOP");
				cars.add(car_1);
				cars.add(car_2);
				cars.add(car_3);
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
		} else {
			try {
				int count = 1;
				// We will add all the cars in the cars files to the arraylist
				while (car_list.exists()) {
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
					// We increase the counter and check if the next file exists at the start of the loop 
					count++;
					car_list = new File("car_list_" + String.valueOf(count) + ".txt");
					br.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public static void checkEntriesFile() {
		int count = 1;
		if(entries.exists()) {
			count ++;
		} else {
			entries = new File("entries_" + String.valueOf(count) + ".txt");
		}
	}
	
	public static void checkExitsFile() {
		int count = 1;
		if(exits.exists()) {
			count ++;
		} else {
			exits = new File("exits_" + String.valueOf(count) + ".txt");
		}
	}
	
	public static void CreateNewFile() throws IOException {
		
		// First we let the user choose the new file type (cars, entries or exits)
		String [] options = {"Create vehicles file", "Create entries file", "Create exits file", "Exit"};
		int select = JOptionPane.showOptionDialog(null, "Choose the file type",
				"Parking Manager", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
				options[0]);
		int count = 1;
		boolean exit = false;
		File file;
		
		// Then we count the number of that type of file existing and we create one more
		// with the name and the counter
		switch (select) {
		case 0:
			while (exit != true) {
				file = new File("car_list_" + String.valueOf(count) + ".txt");
				if (file.exists()) {
					count++;
				} else {
					file.createNewFile();
					car_list = file;
					break;
				}
			}
			break;
		case 1:
			while (exit != true) {
				file = new File("entries_" + String.valueOf(count) + ".txt");
				if (file.exists()) {
					count++;
				} else {
					file.createNewFile();
					entries = file;
					break;
				}
			}
			break;
		case 2:
			while (exit != true) {
				file = new File("exits_" + String.valueOf(count) + ".txt");
				if (file.exists()) {
					count++;
				} else {
					file.createNewFile();
					exits = file;
					break;
				}
			}
			break;
		case 3:
			return;
		}
	}
}