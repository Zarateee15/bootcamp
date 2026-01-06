// Desarrollado por Jorge Zárate
/* Crea una aplicación que nos pida un día de la semana y que nos diga si es un dia
laboral o no (“De lunes a viernes consideramos dias laborales”) */

import java.util.Scanner;

public class ejercicio_10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] diasLaborales = {"lunes", "martes", "miercoles", "jueves", "viernes"};
        String[] diasNoLaborales = {"sabado", "domingo"};
        String dia_ingresado;
        boolean noEsDia = true;

        System.out.println("Ingrese un dia de la semana para ver si es laboral o no!");
        do {
            dia_ingresado = sc.nextLine();
            for (String aux : diasLaborales) {
                if (aux.equals(dia_ingresado.toLowerCase())) {
                    System.out.println("Es dia laboral kp!");
                    noEsDia = false;
                    break;
                }
            }
            for (String aux : diasNoLaborales) {
                if (aux.equals(dia_ingresado.toLowerCase())) {
                    System.out.println("No es dia laboral kp, ja'upy!");
                    noEsDia = false;
                    break;
                }
            }
            if (noEsDia) {
                System.out.println("Error. Vuelva a ingresarlo.");
            }
        } while (noEsDia);
    }
}
