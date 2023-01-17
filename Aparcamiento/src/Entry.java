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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {
	
	public static JFileChooser filechooser = new JFileChooser();
	
	public static void showEntries() throws IOException {
		
		filechooser.setDialogTitle("Enter the entries file");
		filechooser.showOpenDialog(filechooser);
		String entries = filechooser.getSelectedFile().getAbsolutePath();
		
		if (entries.contains("entries")) {
			
			FileReader fr = new FileReader(entries);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			String text = "";

			// Getting the file content
			while ((line = br.readLine()) != null) {
				text += line + "\n";
			}
			br.close();

			// Creating a new window with a scroll that shows all the entries found in the file
			JTextArea textArea = new JTextArea(text);
			JScrollPane scrollPane = new JScrollPane(textArea);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(null, scrollPane, "Car list", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Select a entries file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void newEntry(File entries) throws IOException {
		
		String id = JOptionPane.showInputDialog("Enter the car's id: ");
		int i;
		// Checking if the car is registered
		boolean aux = false;
		for(i = 0; i < Main.cars.size(); i++) {
			if(Main.cars.get(i).getId().equals(id)) {
				aux = true;
				break;
			}
		}
		if(aux == false) {
			JOptionPane.showMessageDialog(null, "The id entered was not found", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		FileWriter fw = new FileWriter(entries, true);
		
		fw.write(Main.cars.get(i).toString() + " " + formatter.format(date));
		fw.flush();
		fw.close();
	}
}
