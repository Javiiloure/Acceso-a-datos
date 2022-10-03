import java.io.*;
public class Tareaficheros {
	public static void main(String [] arg) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			archivo = new File("C:\\Users\\javie\\Desktop/archivo.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			//Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null) {
				System.out.println(linea);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null!=fr) {
					fr.close();
				}
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
