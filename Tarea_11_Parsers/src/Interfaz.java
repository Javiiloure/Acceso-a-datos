import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

@SuppressWarnings("serial")
public class Interfaz extends javax.swing.JFrame {
	
	protected DOM dom = new DOM();
	protected SAX sax = new SAX();
	protected StAX stax = new StAX();
	protected static JTextArea contenido;
	protected File archivo;
	
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

		setTitle("Parsers XML");
		getContentPane().setLayout(null);

		// Creamos un label para mostrar el estado del archivo
		JLabel estado = new JLabel("Estado: Archivo no seleccionado");
		estado.setBounds(43, 67, 395, 82);
		estado.setFont(new Font("Dialog", Font.PLAIN, 12));
		getContentPane().add(estado);

		// Creamos un textArea y le añadimos un scroll
		contenido = new JTextArea();
		contenido.setBounds(58, 180, 303, 169);
		JScrollPane scrollBar = new JScrollPane(contenido);
		scrollBar.setBounds(43, 172, 558, 452);
		contenido.setEditable(false);
		getContentPane().add(scrollBar);

		// Creamos y añadimos boton de limpiar
		JButton limpiar = new JButton("Limpiar");
		limpiar.setBounds(446, 119, 155, 42);
		limpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				contenido.setText("");
			}
		});
		getContentPane().add(limpiar);
		
		// Creamos y añadimos boton de seleccionar archivo
		JButton seleccionar = new JButton("Seleccionar archivo");
		seleccionar.setBounds(446, 67, 155, 41);
		seleccionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setDialogTitle("Seleccione un archivo XML");
				filechooser.addChoosableFileFilter(new FileNameExtensionFilter(
					     "xml files (*.xml)", "xml"));
				filechooser.showOpenDialog(filechooser);
				archivo = filechooser.getSelectedFile();
				estado.setText("Estado: " + archivo.getAbsolutePath());
			}
 		});
		getContentPane().add(seleccionar);
		
		// Creamos una MenuBar y le añadimos los diferentes menus
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new GridLayout(1, 5, 0, 0));
		menuBar.setBounds(0, 0, 626, 56);
		getContentPane().add(menuBar);

		// Menu con submenu Gestionar DOM
		JMenu gestionar_DOM = new JMenu("Gestionar_DOM");
		JMenuItem crear_XML = new JMenuItem("Crear XML");
		JMenuItem mostrar_XML = new JMenuItem("Mostrar XML");
		JMenuItem añadir_nodo = new JMenuItem("Añadir nodo");
		crear_XML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				try {
					dom.crearXML();
				} catch(Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mostrar_XML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				if(archivo != null) {
				try {
					dom.mostrarXML(contenido, archivo);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				} else {
					JOptionPane.showMessageDialog(null, "No hay ningun archivo seleccionado.",
						      "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		añadir_nodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				if(archivo != null){
					try {
						dom.añadirNodo(contenido, archivo);
					} catch(Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No hay ningun archivo seleccionado.",
						      "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gestionar_DOM.add(crear_XML);
		gestionar_DOM.add(mostrar_XML);
		gestionar_DOM.add(añadir_nodo);
		menuBar.add(gestionar_DOM);

		// Menu con submenu Gestionar SAX
		JMenu gestionar_SAX = new JMenu("Gestionar_SAX");
		JMenuItem crear_evento = new JMenuItem("Crear evento");
		JMenuItem mostrar_evento = new JMenuItem("Mostrar evento");
		JMenuItem añadir_evento = new JMenuItem("Añadir evento");
		crear_evento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				sax.crearEvento();
			}
		});
		mostrar_evento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				sax.mostrarEvento(contenido);
			}
		});
		añadir_evento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				sax.añadirEvento();
			}
		});
		gestionar_SAX.add(crear_evento);
		gestionar_SAX.add(mostrar_evento);
		gestionar_SAX.add(añadir_evento);
		menuBar.add(gestionar_SAX);

		// Menu con submenu Gestionar StAX
		JMenu gestionar_StAX = new JMenu("Gestionar_StAX");
		JMenuItem crear = new JMenuItem("Crear");
		JMenuItem mostrar = new JMenuItem("Mostrar");
		JMenuItem APICursor = new JMenuItem("APICursor");
		JMenuItem APIEvents = new JMenuItem("APIEvents");
		crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				stax.crear();
			}
		});
		mostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				stax.mostrar();
			}
		});
		APICursor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				stax.APICursor();
			}
		});
		APIEvents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				stax.APIEvents();
			}
		});
		gestionar_StAX.add(crear);
		gestionar_StAX.add(mostrar);
		gestionar_StAX.add(APICursor);
		gestionar_StAX.add(APIEvents);
		menuBar.add(gestionar_StAX);

		// Menu con submenu Validacion
		JMenu validacion = new JMenu("Validacion");
		JMenuItem xsd = new JMenuItem("XSD");
		JMenuItem dtd = new JMenuItem("DTD");
		xsd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev){
				
			}
		});
		dtd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ev) {
				
			}
		});
		validacion.add(xsd);
		validacion.add(dtd);
		menuBar.add(validacion);

		// Menu Gestion JAXB
		JMenu gestion_JAXB = new JMenu("Gestion JAXB");
		menuBar.add(gestion_JAXB);
	}
}
