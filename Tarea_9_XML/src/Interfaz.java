import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Interfaz extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz interfaz = new Interfaz();
					interfaz.setVisible(true);
					interfaz.setResizable(false);
					interfaz.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Interfaz() {
		setDefaultCloseOperation(Interfaz.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 698);
		
		setTitle("Lector XML");
		getContentPane().setLayout(null);
		
		// Creamos un textArea y le añadimos un scroll
		JTextArea texto = new JTextArea();
		texto.setBounds(58, 180, 303, 169);
		texto.setEditable(false);
		JScrollPane scrollBar = new JScrollPane(texto);
		scrollBar.setBounds(43, 144, 558, 480);
		getContentPane().add(scrollBar);
		
		JButton crearXML = new JButton("Crear XML");
		crearXML.setBounds(61, 40, 127, 35);
		getContentPane().add(crearXML);
		crearXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.crearXML();			}
		}); 
		
		JButton mostrarXML = new JButton("Mostrar XML");
		mostrarXML.setBounds(419, 40, 127, 35);
		getContentPane().add(mostrarXML);
		mostrarXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.mostrarXML(texto);
			}
		});
	}
}
