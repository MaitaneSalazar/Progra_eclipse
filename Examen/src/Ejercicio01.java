import java.util.Scanner;

public class Ejercicio01 {

	public static void main(String[] args) {
		Scanner sc;
		int num, contSecuencia;

		contSecuencia = 0;

		sc = new Scanner(System.in);
		do {
			System.out.print("Introduce números (introduce 0 para terminar): ");
			num=sc.nextInt();
			if(num==4) {
				System.out.print("Introduce números (introduce 0 para terminar): ");
				num=sc.nextInt();
				if (num==5) {
					System.out.print("Introduce números (introduce 0 para terminar): ");
					num=sc.nextInt();
					if (num==6) {
						System.out.print("Introduce números (introduce 0 para terminar): ");
						num=sc.nextInt();
						if(num==7) {
							contSecuencia++;
						}
					}
				}
			}
		}while(num!=0);

		System.out.println("La secuencia 4, 5, 6, 7 ha salido " + contSecuencia + " veces");
	}

}
