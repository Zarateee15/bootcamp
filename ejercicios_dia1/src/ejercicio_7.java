// Desarrollado por Jorge Zárate

public class ejercicio_7 {
    public static void main(String[] args) {
        System.out.print("Divisibles entre 2 y 3: ");
        for (int i = 1; i < 101; i++) {
            if (i%2 == 0 && i%3==0){
                System.out.print(" "+i+" ");
            };
        }
    }
}