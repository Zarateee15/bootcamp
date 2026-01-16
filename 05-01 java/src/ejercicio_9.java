// Desarrollado por Jorge Zárate
import java.util.Scanner;

public class ejercicio_9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String contrasenha = "taquerola";
        String contra_text;
        int intentos = 0;

        System.out.println("--------------------------------------------------");
        System.out.println("        Ingrese su contraseña:");
        System.out.println("--------------------------------------------------");
        do {
            contra_text = sc.nextLine();
            intentos+=1;
            if (contra_text.equals(contrasenha)){
                System.out.println("Correcto :P");
                break;
            } else if (intentos!=3) {
                System.out.println("Contraseña Incorrecta. Vuelve a intentarlo.");
            } else {
                System.out.println("FALLASTE JAJAJAJ BOBAZO.");
                break;
            }
        }while(true);
    }
}