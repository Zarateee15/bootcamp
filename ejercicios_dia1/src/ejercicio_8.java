// Desarrollado por Jorge Zárate
import java.util.Scanner;

public class ejercicio_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        int aux = 0;

        System.out.println("Ingresa un numero: ");
        do{
            num = sc.nextInt();
            if (num >= 0){
                aux = 1;
            } else {
                System.out.println("ERROR. Debe ser mayor o igual a 0. Vuelva a ingresarlo.");
            }
        } while (aux != 1);

        System.out.println("El numero es: "+num);
    }
}