/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_de_animales;

/**
 *
 * @author ben_9
 */
public class Animal {

    protected String nombre;
    protected int edad;

    protected Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void comer() {
        System.out.println("El animal come");
    }

    public void hacerSonido() {
        System.out.println("El animal hace un sonido");
    }

    public void mostrarDatos() {
        System.out.println("NOMBRE:: " + this.nombre);
        System.out.println("EDAD:: " + this.edad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
