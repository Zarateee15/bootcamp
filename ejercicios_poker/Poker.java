package ejercicios_poker;

public class Poker {
    public static void verificarJugada(Carta[] mazo){

        int cantidad_jugadads = 9;

        //for (int i = 0; i < cantidad_jugadads; i++){
            for (int j = 0; j < mazo.length; j++) {
                String valor = mazo[j].getValor();
                String palo = mazo[j].getPalo();
                System.out.println("El valor es:" + valor);
                System.out.println("El palo es:" + palo);



                
            }
        //}
    }
}
