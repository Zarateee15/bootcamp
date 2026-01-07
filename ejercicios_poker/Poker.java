package ejercicios_poker;
import java.util.*;

public class Poker {
    public static void verificarJugada(Carta[] mazo){
        int cont = 0;
        Boolean esColor = false;
        Boolean esEscalera = false;
        boolean esPoker = false;
        boolean esTrio = false;
        boolean esFull = false;
        boolean esParDoble = false;
        boolean esPar = false;
        String mazo0 = mazo[0].getValor();
        String mazoF = mazo[mazo.length-1].getValor();

        do {  
            int aux = 0; // auxiliar para las posibles jugadas
            cont += 1; // contador de posibles jugadas

            // Recorre el mazo
            for (int j = 0; j <= mazo.length - 1; j++) {
                String v = mazo[j].getValor();  // Obtiene el valor en STRING de la carta actual del mazo
                String palo = mazo[j].getPalo(); // Obtiene el palo de la carta actual del mazo
                int pos;
                
                if (mazoF.equals("A")){ pos = 2; } else { pos = 1;}          

                int valor = convertirValor(v,pos);

                int x;
                String y;
                // Jugadas Posibles
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

                    case 4: // Conteo de Valores
                        pos = 1;
                        
                        Map<Integer, Integer> conteo = new HashMap<>();

                        for (Carta c : mazo) {
                            int z = convertirValor(c.getValor(), pos);
                            conteo.put(z, conteo.getOrDefault(z, 0) + 1);
                        }

                        Collection<Integer> repeticiones = conteo.values();

                        esPoker = repeticiones.contains(4);
                        esTrio  = repeticiones.contains(3);

                        int pares = 0;
                        for (int u : repeticiones) {
                            if (u == 2) pares++;
                        }

                        esFull = esTrio && pares == 1;   // (3 y 2)
                        esParDoble = pares == 2;          // (2 y 2)
                        esPar = pares == 1 && !esTrio;   // solo un par

                        /*  Mostrar resultados
                        for (Map.Entry<Integer, Integer> e : conteo.entrySet()) {
                            System.out.println(e.getKey() + " sale " + e.getValue() + " veces");
                        }*/

                        cont = 4;
                        break;
                } 
            }
        }while (cont < 4);
        jugadasPosibles(esColor, esEscalera, esPoker, esTrio, esFull, esParDoble, esPar);
    }



    public static int convertirValor(String aux, int pos){

        // Manejo de los valores T=10, J=11, Q=12, K=13, A=1 o A=14 dependiendo de la ubicacion
        switch (aux) {    
            case "T": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": 
                if (pos == 2){ return 14; } else { return 14; }
            default: return Integer.parseInt(aux);
        }
    }

    public static void jugadasPosibles(Boolean esColor, Boolean esEscalera, Boolean esPoker, Boolean esTrio, Boolean esFull, Boolean esParDoble, Boolean esPar){
        if((esColor) && (esEscalera)){ System.out.println("Se puede jugar Escalera Color!"); } 
        else if (esPoker) { System.out.println("Se puede jugar Poker!"); }
        else if (esFull) { System.out.println("Se puede jugar Full House!"); }
        else if (esColor) { System.out.println("Se puede jugar Color!"); }
        else if (esEscalera) { System.out.println("Se puede jugar Escalera!"); }
        else if (esTrio) { System.out.println("Se puede jugar Trio!"); }
        else if (esParDoble) { System.out.println("Se puede jugar Par Doble!"); }
        else if (esPar) { System.out.println("Se puede jugar Par!"); }
        else { System.out.println("Se puede jugar Carta!"); }
}
}
