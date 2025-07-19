/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tic_tac_toe;

/**
 * Representa un jugador en el juego de Tic-Tac-Toe.
 * Cada jugador tiene un nombre, un caracter que representa su ficha en el tablero,
 * y un contador de partidas ganadas.
 * 
 * @author ben_9
 */
public class Jugador {
    /** Caracter que representa la ficha del jugador en el tablero (por ejemplo, "X" o "O"). */
    protected String caracter;
    /** Nombre del jugador. */
    protected String nombre;
    /** Número de partidas ganadas por el jugador. */
    protected int partidasGanadas = 0;

    /**
     * Constructor de la clase Jugador.
     * 
     * @param caracter el caracter que representa la ficha del jugador.
     * @param nombre el nombre del jugador.
     */
    public Jugador(String caracter, String nombre) {
        this.caracter = caracter;
        this.nombre = nombre;
    }

    /**
     * Establece el caracter que representa la ficha del jugador.
     * 
     * @param caracter el nuevo caracter del jugador.
     */
    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    /**
     * Establece el nombre del jugador.
     * 
     * @param nombre el nuevo nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el número de partidas ganadas por el jugador.
     * 
     * @param partidasGanadas el nuevo número de partidas ganadas.
     */
    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    /**
     * Obtiene el caracter que representa la ficha del jugador.
     * 
     * @return el caracter del jugador.
     */
    public String getCaracter() {
        return caracter;
    }

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el número de partidas ganadas por el jugador.
     * 
     * @return el número de partidas ganadas.
     */
    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    /**
     * Incrementa en 1 el número de partidas ganadas por el jugador.
     * 
     * @return el nuevo número de partidas ganadas.
     */
    public int agregarVictoria() {
        this.partidasGanadas = this.partidasGanadas + 1;
        return this.partidasGanadas;
    }
}
