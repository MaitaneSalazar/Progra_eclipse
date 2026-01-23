import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Fichero {
	
	public Fichero() {
		
	}
	
	public ArrayList<String> cargarPalabras(String fichero) {
		Scanner scFichero;
		ArrayList<String> palabras;
		palabras=new ArrayList<String>();
		try {
			scFichero=new Scanner(new File("./recursos/"+fichero));
			while(scFichero.hasNext()) {
				palabras.add(scFichero.nextLine());
			}
			scFichero.close();
			return palabras;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Image[] leerImagenes()  {
		Image[] arrayImagenes;
		arrayImagenes = new Image[8];
		//Leer todos los ficheros del ahorcardo y guardarlos en cada posición del array
		for(int i= 0; i < arrayImagenes.length; i++) {
			//File file = new File("./recursos/Ahorcado"+i+ ".png"); //con el fichero si hay que especificar la ruta de acceso
			//arrayImagenes[i] = ImageIO.read(file);
			arrayImagenes[i] = new ImageIcon(getClass().getResource("Ahorcado"+i+ ".png")).getImage(); //No hay que especificar la carpeta porque no puede haber dos recursos con el mismo nombre
			//arrayImagenes[i] = Toolkit.getDefaultToolkit().getImage("Ahorcado"+i+ ".png");
		}
		
		return arrayImagenes;
	}

	public Image[] leerSprite() {
		Image[] arrayImagenes;
		BufferedImage bfImage;
		arrayImagenes = new Image[8];
		try {
			bfImage=ImageIO.read(new File("./recursos/spriteAhorcado.jpg"));
			for(int i= 0; i < arrayImagenes.length; i++) {
				if(i<2) {
					arrayImagenes[i] = bfImage.getSubimage(813/5*(i%5), (321/2)*(i/5)/*0 1 2 3 y 4 da 0 porque es integer*/, 813/5, 321/2);
				} else {
					arrayImagenes[i] = bfImage.getSubimage(813/5*((i+1)%5), (321/2)*((i+1)/5), 813/5, 321/2); //saltar el tercer sprite de la imagen
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayImagenes;
	}
}
