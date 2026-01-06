// Desarrollado por Jorge Zárate

public class ejercicio_7 {
    public static void main(String[] args) {
        System.out.print("Divisibles entre 2: ");
        for (int i = 1; i < 101; i++) {
            if (i%2 == 0){
                System.out.print(" "+i+" ");
            };
        }
        System.out.print("\nDivisibles entre 3: ");
        for (int j = 1; j < 101; j++) {
            if (j%3 == 0){
                System.out.print(" "+j+" ");
            };
        }
    }
}