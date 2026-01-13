
public class Ejercicio02 {

	public static void main(String[] args) {
		int []arrayNumeros;

		arrayNumeros = new int [40];

		llenarArray(arrayNumeros);

		mostrarArray(arrayNumeros);

		numMayor(arrayNumeros);
	}

	public static void llenarArray(int []arrayNum) {
		int max, min, num;

		max = 10;
		min = -10;
		for(int pos=0; pos<arrayNum.length; pos++) {
			num= (int)(Math.random() * ((max+1) - (min-1)) + (min-1));
			arrayNum[pos]=num;
		}
	}

	public static void mostrarArray(int []arrayNum) {
		int cont;
		cont=0;
		for (int i = 0; i < arrayNum.length; i++) {
			cont++;
			if(cont==20){
				System.out.println(arrayNum[i] + " ");
				cont=0;
			}else {
				System.out.print(arrayNum[i] + " ");
			}
		}
	}

	public static void numMayor(int []arrayNum) {
		int contMayor;
		contMayor=0;
		for(int pos=0; pos<arrayNum.length; pos++) {
			if(arrayNum[pos]==10) {
				contMayor++;
			}
		}
		System.out.println("Las veces que ha salido el número mas alto del array son: " + contMayor);
	}

}
