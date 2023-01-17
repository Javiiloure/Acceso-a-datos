import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class Car {

	public static JFileChooser filechooser = new JFileChooser();

	public static int count = 1;
	String id;
	String brand;
	String color;
	String plate;

	public Car() {
		this.id = String.valueOf(count);
		count++;
		this.brand = "";
		this.color = "";
		this.plate = "";
	}

	public Car(String brand, String color, String plate) {
		this.id = String.valueOf(count);
		count++;
		this.brand = brand;
		this.color = color;
		this.plate = plate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", color=" + color + ", plate=" + plate + "]";
	}

	public static void showVehicles() throws IOException {

		filechooser.setDialogTitle("Enter the cars file");
		filechooser.showOpenDialog(filechooser);
		String cars = filechooser.getSelectedFile().getAbsolutePath();

		// Checking if the user has selected a car list file
		if (cars.contains("car_list")) {

			FileReader fr = new FileReader(cars);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String text = "";

			// Getting the file content
			while ((line = br.readLine()) != null) {
				text += line + "\n";
			}
			br.close();

			// Creating a new window with a scroll that shows all the cars found in the file
			JTextArea textArea = new JTextArea(text);
			JScrollPane scrollPane = new JScrollPane(textArea);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(null, scrollPane, "Car list", JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, "Select a car list file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void createVehicle(File car_list) {
		
		// Asking for the car attributes
		String brand = JOptionPane.showInputDialog("Enter the vehicles' brand: ");
		String color = JOptionPane.showInputDialog("Enter the vehicles' color: ");
		String plate = JOptionPane.showInputDialog("Enter the vehicles' plate: ");
		
		// Crating the car object and adding it to the ArrayList in main
		Car car = new Car(brand, color, plate);
		Main.cars.add(car);
		
		try {
			FileWriter fw = new FileWriter(car_list, true);
			fw.write(car.toString());
			fw.flush();
			fw.close();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Input/Output data error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
