import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ejercicio03 {

	public static void main(String[] args) {
		String []arrayJuegos;
		int []arrayPrecios;
		String strLinea;
		int strNum;
		int precioTotal;
		Scanner sc;

		sc = new Scanner(System.in);
		arrayJuegos = new String [8];
		arrayPrecios = new int [8];
		precioTotal=0;

		try {
			sc = new Scanner(new File("Juegos.txt"));
			int contJuegos = 1, contPrecios = 1;
			while(sc.hasNext()) {
				if(sc.hasNextInt()) {
					strNum=sc.nextInt();
					arrayPrecios[contPrecios-1]=strNum;
					contPrecios++;
				}else if(sc.hasNextLine())
				{
					if(contJuegos<16) {
						strLinea=sc.nextLine();
						arrayJuegos[contJuegos/2]=strLinea;
						contJuegos++;
					} else {
						break;
					}
				}

			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("No se puede encontrar el fichero");
		}
		sc.close();

		System.out.println("Listado de juegos comprados:");
		System.out.println();

		for (int i = 0; i < arrayJuegos.length; i++) {
			if (i>arrayJuegos.length-2)
				System.out.println(arrayJuegos[i]);
			else {
				System.out.print(arrayJuegos[i] + ", ");
			}
			
		}

		System.out.println();
		System.out.println();

		for (int i = 0; i < arrayPrecios.length; i++) {
			precioTotal+=arrayPrecios[i];
		}

		System.out.println("Precio total: " + precioTotal + "€");
	}

}
