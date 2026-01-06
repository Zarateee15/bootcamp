package ejercicios_poker;

public class Main {
    public static void main(String[] args) {
        
        // Se declara una variable 'Mazo' de tipo Carta
        Carta[] mazo = new Carta[5];

        // Generar las 5 cartas del mazo
        for (int i = 0; i < mazo.length; i++) {
            mazo[i] = Carta.generarCartaAleatoria();
        }

        // Mostrar las 5 cartas del mazo
        System.out.println("Las cartas del mazo son: ");
        for (int j = 0; j < mazo.length; j++) {  
            System.out.println(mazo[j]);  
        }

        Poker.verificarJugada(mazo);
    }
}