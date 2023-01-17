import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;
public class Exit {
	
	public static JFileChooser filechooser = new JFileChooser();
	
	public static void showExits() throws IOException{
		
		filechooser.setDialogTitle("Enter the exits file");
		filechooser.showOpenDialog(filechooser);
		String exits = filechooser.getSelectedFile().getAbsolutePath();
		
		if (exits.contains("exits")) {
			
			FileReader fr = new FileReader(exits);
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
			JOptionPane.showMessageDialog(null, "Select a exits file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void newExit(File exits) {
		
	}
}
