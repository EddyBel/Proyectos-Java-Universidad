package com.mycompany.tic_tac_toe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

/**
 * La clase IA implementa la lógica para que la inteligencia artificial (IA)
 * juegue al Tic Tac Toe. Toma decisiones basadas en un conjunto de reglas
 * estratégicas que incluyen movimientos ganadores, defensivos, y el algoritmo
 * Minimax con poda alpha-beta para determinar el mejor movimiento posible.
 */
public class IA {

    private JLabel[] todasLasCasillas;
    private Jugador jugador1;
    private Jugador jugador2;
    private int[][] formasDeVictoria;

    /**
     * Constructor de la clase IA.
     *
     * @param todasLasCasillas Un arreglo de JLabel que representa las casillas
     * del tablero.
     * @param jugador1 El jugador 1.
     * @param jugador2 El jugador 2 (IA).
     * @param formasDeVictoria Un arreglo bidimensional con las combinaciones
     * ganadoras del juego.
     */
    public IA(JLabel[] todasLasCasillas, Jugador jugador1, Jugador jugador2, int[][] formasDeVictoria) {
        this.todasLasCasillas = todasLasCasillas;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.formasDeVictoria = formasDeVictoria;
    }

    /**
     * Realiza el siguiente movimiento de la IA. La IA selecciona un movimiento
     * según las reglas de prioridad: 1. Primero elige una jugada ganadora. 2.
     * Luego, bloquea una jugada ganadora del oponente. 3. Después, busca una
     * jugada de doble amenaza. 4. Si no hay ninguna jugada prioritaria, usa el
     * algoritmo Minimax con poda alpha-beta.
     */
    public void realizarMovimiento() {
        int movimiento = obtenerMovimientoIA();
        if (movimiento != -1) {
            todasLasCasillas[movimiento].setText(jugador2.getCaracter());
            todasLasCasillas[movimiento].setForeground(Color.BLUE);
        }
    }

    /**
     * Obtiene el siguiente movimiento de la IA basándose en un conjunto de
     * reglas estratégicas. La IA prioriza diferentes tipos de jugadas para
     * asegurar una victoria, bloquear una victoria del oponente o crear
     * oportunidades de doble amenaza. Si no hay jugadas inmediatas, utiliza un
     * algoritmo Minimax con poda alpha-beta para calcular el mejor movimiento
     * posible.
     *
     * @return El índice de la casilla en la que la IA debe realizar su
     * movimiento. Retorna -1 si no se encuentra un movimiento adecuado.
     */
    private int obtenerMovimientoIA() {
        // 1. Evaluar si es la primera jugada
        if (esPrimeraJugada()) {
            // Si es la primera jugada, la IA elige una posición estratégica inicial, como el centro o las esquinas.
            return elegirPrimerMovimiento();
        }

        // 2. Comprobar si la IA tiene una jugada ganadora
        int movimiento = buscarJugadaGanadora(jugador2);
        if (movimiento != -1) {
            // Si la IA encuentra una jugada ganadora, la realiza.
            return movimiento;
        }

        // 3. Comprobar si el oponente tiene una jugada ganadora y bloquearla
        movimiento = buscarJugadaGanadora(jugador1);
        if (movimiento != -1) {
            // Si el oponente tiene una jugada ganadora, la IA bloquea esa casilla.
            return movimiento;
        }

        // 4. Buscar una jugada de doble amenaza para la IA
        // Una doble amenaza se refiere a crear una jugada en la que la IA puede ganar en dos lugares diferentes.
        movimiento = buscarDobleAmenaza();
        if (movimiento != -1) {
            // Si la IA puede crear una doble amenaza, la ejecuta.
            return movimiento;
        }

        // 5. Si no hay jugadas ganadoras, de bloqueo o de doble amenaza, usar el algoritmo Minimax con poda alpha-beta
        // para evaluar el mejor movimiento posible.
        return minimaxConPodaAlphaBeta(3, Integer.MIN_VALUE, Integer.MAX_VALUE, true).getIndex();
    }

    /**
     * Determina si es el primer movimiento del juego (cuando el tablero está
     * vacío).
     *
     * @return True si es el primer movimiento, false en caso contrario.
     */
    private boolean esPrimeraJugada() {
        for (JLabel casilla : todasLasCasillas) {
            if (!casilla.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Elige un movimiento inicial en posiciones estratégicas (centro y
     * esquinas).
     *
     * @return El índice de una casilla disponible en las posiciones iniciales
     * recomendadas.
     */
    private int elegirPrimerMovimiento() {
        List<Integer> primerasOpciones = List.of(0, 2, 6, 8, 4);
        List<Integer> opcionesDisponibles = new ArrayList<>();
        for (int pos : primerasOpciones) {
            if (todasLasCasillas[pos].getText().isEmpty()) {
                opcionesDisponibles.add(pos);
            }
        }
        if (!opcionesDisponibles.isEmpty()) {
            int indexAleatorio = (int) (Math.random() * opcionesDisponibles.size());
            return opcionesDisponibles.get(indexAleatorio);
        }
        return -1;
    }

    /**
     * Busca una jugada ganadora para el jugador dado.
     *
     * @param jugador El jugador para el cual se está buscando una jugada
     * ganadora.
     * @return El índice de la casilla donde se debe hacer el movimiento, o -1
     * si no se encuentra una jugada ganadora.
     */
    private int buscarJugadaGanadora(Jugador jugador) {
        String caracterJugador = jugador.getCaracter();
        for (int[] combinacion : this.formasDeVictoria) {
            JLabel casilla1 = this.todasLasCasillas[combinacion[0]];
            JLabel casilla2 = this.todasLasCasillas[combinacion[1]];
            JLabel casilla3 = this.todasLasCasillas[combinacion[2]];

            if (casilla1.getText().equals(caracterJugador) && casilla2.getText().equals(caracterJugador) && casilla3.getText().isEmpty()) {
                return combinacion[2];
            }
            if (casilla2.getText().equals(caracterJugador) && casilla3.getText().equals(caracterJugador) && casilla1.getText().isEmpty()) {
                return combinacion[0];
            }
            if (casilla1.getText().equals(caracterJugador) && casilla3.getText().equals(caracterJugador) && casilla2.getText().isEmpty()) {
                return combinacion[1];
            }
        }
        return -1;
    }

    /**
     * Busca una jugada en la que la IA pueda crear una "doble amenaza", es
     * decir, una situación en la que la IA pueda ganar en dos lugares
     * diferentes en el próximo turno, lo que obliga al oponente a bloquear una
     * de las dos amenazas, dejando la otra libre para que la IA gane.
     *
     * Este método evalúa todas las posibles combinaciones de tres casillas que
     * pueden formar una línea ganadora, verificando si dos de las casillas de
     * la combinación están ocupadas por el marcador de la IA (jugador2) y la
     * tercera casilla está vacía. Si se encuentra tal combinación, la IA puede
     * hacer su movimiento en la casilla vacía y crear una doble amenaza.
     *
     * El método recorre las combinaciones de victoria predefinidas y, para cada
     * una de ellas, verifica si hay dos casillas ocupadas por la IA y la
     * tercera vacía. Si encuentra alguna de estas situaciones, devuelve el
     * índice de la casilla vacía en la combinación correspondiente.
     *
     * Si no se encuentra ninguna jugada de doble amenaza, el método retorna -1,
     * indicando que no es posible crear una doble amenaza en ese momento.
     *
     * @return El índice de la casilla en la que la IA debe hacer su movimiento
     * para crear una doble amenaza, o -1 si no es posible.
     */
    private int buscarDobleAmenaza() {
        // Recorre todas las combinaciones de victoria posibles
        for (int[] combinacion : this.formasDeVictoria) {
            // Asigna las tres casillas que forman una combinación de victoria
            JLabel casilla1 = this.todasLasCasillas[combinacion[0]];
            JLabel casilla2 = this.todasLasCasillas[combinacion[1]];
            JLabel casilla3 = this.todasLasCasillas[combinacion[2]];

            // Verifica si dos casillas están ocupadas por el jugador IA y la tercera está vacía
            if (casilla1.getText().equals(jugador2.getCaracter()) && casilla2.getText().equals(jugador2.getCaracter()) && casilla3.getText().isEmpty()) {
                return combinacion[2]; // Devuelve la casilla vacía que completa la doble amenaza
            }
            if (casilla2.getText().equals(jugador2.getCaracter()) && casilla3.getText().equals(jugador2.getCaracter()) && casilla1.getText().isEmpty()) {
                return combinacion[0]; // Devuelve la casilla vacía que completa la doble amenaza
            }
            if (casilla1.getText().equals(jugador2.getCaracter()) && casilla3.getText().equals(jugador2.getCaracter()) && casilla2.getText().isEmpty()) {
                return combinacion[1]; // Devuelve la casilla vacía que completa la doble amenaza
            }
        }
        // Si no se encuentra ninguna jugada de doble amenaza, retorna -1
        return -1;
    }

    /**
     * Implementa el algoritmo Minimax con poda alpha-beta para determinar el
     * mejor movimiento.
     *
     * @param profundidad La profundidad de búsqueda.
     * @param alpha El valor mínimo aceptable para la IA.
     * @param beta El valor máximo aceptable para el oponente.
     * @param esMaximizar Indica si se está buscando maximizar la puntuación de
     * la IA o minimizar la del oponente.
     * @return El movimiento óptimo basado en el algoritmo Minimax.
     */
    private Movimiento minimaxConPodaAlphaBeta(int profundidad, int alpha, int beta, boolean esMaximizar) {
        List<Integer> casillasDisponibles = obtenerCasillasDisponibles();
        if (profundidad == 0 || casillasDisponibles.isEmpty()) {
            return new Movimiento(evaluarTablero(), -1);
        }

        int mejorValor = esMaximizar ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int mejorMovimiento = -1;

        for (int casilla : casillasDisponibles) {
            todasLasCasillas[casilla].setText(esMaximizar ? jugador2.getCaracter() : jugador1.getCaracter());
            Movimiento resultado = minimaxConPodaAlphaBeta(profundidad - 1, alpha, beta, !esMaximizar);
            todasLasCasillas[casilla].setText("");

            if (esMaximizar) {
                if (resultado.getValor() > mejorValor) {
                    mejorValor = resultado.getValor();
                    mejorMovimiento = casilla;
                }
                alpha = Math.max(alpha, mejorValor);
            } else {
                if (resultado.getValor() < mejorValor) {
                    mejorValor = resultado.getValor();
                    mejorMovimiento = casilla;
                }
                beta = Math.min(beta, mejorValor);
            }

            if (beta <= alpha) {
                break;
            }
        }

        return new Movimiento(mejorValor, mejorMovimiento);
    }

    /**
     * Evalúa el estado del tablero, asignando una puntuación que representa la
     * ventaja de la IA.
     *
     * @return La puntuación del tablero.
     */
    private int evaluarTablero() {
        int puntuacion = 0;

        if (buscarJugadaGanadora(jugador2) != -1) {
            puntuacion += 10;
        }
        if (buscarJugadaGanadora(jugador1) != -1) {
            puntuacion -= 10;
        }

        return puntuacion;
    }

    /**
     * Obtiene una lista de casillas disponibles para jugar.
     *
     * @return Una lista de los índices de las casillas disponibles.
     */
    private List<Integer> obtenerCasillasDisponibles() {
        List<Integer> casillasDisponibles = new ArrayList<>();
        for (int i = 0; i < todasLasCasillas.length; i++) {
            if (todasLasCasillas[i].getText().isEmpty()) {
                casillasDisponibles.add(i);
            }
        }
        return casillasDisponibles;
    }

    /**
     * Representa un movimiento en el juego Tic Tac Toe.
     */
    public static class Movimiento {

        private int valor;
        private int index;

        public Movimiento(int valor, int index) {
            this.valor = valor;
            this.index = index;
        }

        public int getValor() {
            return valor;
        }

        public int getIndex() {
            return index;
        }
    }
}
