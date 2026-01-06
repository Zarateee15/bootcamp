package ejercicios_poker;
import java.util.Random;

public class Carta {
    // Static final para que sea por clase (no por objetos) y para no malgastar espacio en memoria.
    private static final String[] VALORES = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private static final String[] PALOS = {"S", "D", "H", "C"};
    
    private String valor;
    private String palo;

    public Carta(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }

    private static final Random random = new Random(); // Random para la clase

    public static Carta generarCartaAleatoria() {

        int v = random.nextInt(VALORES.length);
        String valor = VALORES[v];

        int p = random.nextInt(PALOS.length);
        String palo = PALOS[p];

        return new Carta(valor, palo);
    }

    @Override
    public String toString() {
        return valor + palo;
    }
}

