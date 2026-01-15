package ejercicios_dia2_poker;
import java.util.*;

public class Poker {
    static int aux = 0;

    public static void verificarJugada(Carta[] mazo){
        int cont = 0;
        int jugada = 0;
        String mazo0 = mazo[0].getValor();
        String mazoF = mazo[mazo.length-1].getValor();

        do {  
            cont += 1; // contador de posibles jugadas

            // Recorre el mazo
            for (int j = 0; j <= mazo.length - 1; j++) {
                String v = mazo[j].getValor();  // Obtiene el valor en STRING de la carta actual del mazo
                String palo = mazo[j].getPalo(); // Obtiene el palo de la carta actual del mazo
                int pos;
                
                if (mazoF.equals("A")){ pos = 2; } else { pos = 1;}          

                int valor = convertirValor(v,pos);

                // Jugadas Posibles
                switch (cont) {
                    case 1: // Escalera Color
                        jugada = escaleraColor(mazo, j, cont, mazo0, mazoF, valor, palo, pos);

                    case 2: // Conteo de Valores
                        jugada = conteoValores(mazo);
                        cont = 5;
                        break;
                } 
            }
        }while (cont < 3);
        jugadasPosibles(jugada);
    }



    public static int convertirValor(String aux, int pos){

        // Manejo de los valores T=10, J=11, Q=12, K=13, A=1 o A=14 dependiendo de la ubicacion
        switch (aux) {    
            case "T": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": 
                if (pos == 2){ return 14; } else { return 1; }
            default: return Integer.parseInt(aux);
        }
    }


    public static int escaleraColor (Carta[] mazo, int j, int cont, String mazo0, String mazoF, int valor, String palo, int pos){
        Boolean esColor = false;
        Boolean esEscalera = false;
        int x;
        String y;

        switch (cont) {
            case 1: // Escalera con A=1

                if (mazo0.equals("A")){ pos = 1; } 
                if (j==0){ x = valor-1; } else { x = convertirValor(mazo[j-1].getValor(),pos); }
                if (valor == x+1){  aux += 1; }
                if (aux == 5) {esEscalera = true;}
                break;

            case 2: // Escalera con A=14
                
                if (mazoF.equals("A")){ pos = 2; }
                if (j==0){ x = valor-1; } else { x = convertirValor(mazo[j-1].getValor(),pos); }
                if (valor == x+1){  aux += 1; }
                if (aux == 5){esEscalera = true;}
                break;

            case 3: // Color
                if (j==0){ y = palo; } else { y = mazo[j-1].getPalo();; }
                if (palo.equals(y)){  aux += 1; }
                if (aux == 5){esColor = true;}
                break;
        }

        if(esColor && esEscalera){  return 1; } 
        else if (esColor){ return 4; }
        else if (esEscalera){ return 5; }
        else { return 0;}
    }

    
    public static int conteoValores(Carta[] mazo){
        int pos = 1;
        
        Map<Integer, Integer> conteo = new HashMap<>();

        for (Carta c : mazo) {
            int z = convertirValor(c.getValor(), pos);
            conteo.put(z, conteo.getOrDefault(z, 0) + 1);
        }

        Collection<Integer> repeticiones = conteo.values();

        Boolean esPoker = repeticiones.contains(4);
        Boolean esTrio  = repeticiones.contains(3);

        int pares = 0;
        for (int u : repeticiones) {
            if (u == 2) pares++;
        }

        Boolean esFull = esTrio && pares == 1;   // (3 y 2)
        Boolean esParDoble = pares == 2;          // (2 y 2)
        Boolean esPar = pares == 1 && !esTrio;   // solo un par

        /*  Mostrar resultados
        for (Map.Entry<Integer, Integer> e : conteo.entrySet()) {
            System.out.println(e.getKey() + " sale " + e.getValue() + " veces");
        }*/
        
        if (esPoker)  { return 2; }
        else if (esFull)  { return 3; }
        else if (esTrio)  { return 6; }
        else if (esParDoble)  { return 7; }
        else if (esPar)  { return 8; }
        else { return 9; }
    }

    public static void jugadasPosibles(int jugada){
        //if((esColor) && (esEscalera)){ System.out.println("Se puede jugar Escalera Color!"); } 
        if(jugada == 1){ System.out.println("Se puede jugar Escalera Color!"); }
        else if (jugada == 2) { System.out.println("Se puede jugar Poker!"); }
        else if (jugada == 3) { System.out.println("Se puede jugar Full House!"); }
        else if (jugada == 4) { System.out.println("Se puede jugar Color!"); }
        else if (jugada == 5) { System.out.println("Se puede jugar Escalera!"); }
        else if (jugada == 6) { System.out.println("Se puede jugar Trio!"); }
        else if (jugada == 7) { System.out.println("Se puede jugar Par Doble!"); }
        else if (jugada == 8) { System.out.println("Se puede jugar Par!"); }
        else if (jugada == 9) { System.out.println("Se puede jugar Carta Alta!"); }
        else { System.out.println("ERROR."); }
}
}
