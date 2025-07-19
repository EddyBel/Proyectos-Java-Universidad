/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tienda_de_animales;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ben_9
 */
public class TIENDA_DE_ANIMALES {

    static private final Scanner scan = new Scanner(System.in);
    static protected ArrayList<Animal> animales = new ArrayList<>();

    public static void addNewAnimal(Animal animal) {
        animales.add(animal);
    }

    public static void sonidosAnimales() {
        int cantidadAnimales = animales.size();

        if (cantidadAnimales < 1) {
            System.out.println("NO HAY NINGUN ANIMAL EN LA TIENDA");
        } else {
            for (int i = 0; i < animales.size(); i++) {
                Animal animalActual = animales.get(i);
                animalActual.hacerSonido();
            }
        }

    }

    public static void datosDeLosAnimales() {
        int cantidadAnimales = animales.size();
        if (cantidadAnimales < 1) {
            System.out.println("NO HAY ANIMALES EN LA TIENDA");
        } else {
            for (int i = 0; i < animales.size(); i++) {

                System.out.println("----------------------------------------");
                Animal animalActual = animales.get(i);
                animalActual.mostrarDatos();
                System.out.println("----------------------------------------");

            }
        }
    }

    public static int mostrarMenu() {

        try {
            System.out.println("-------------------");
            System.out.println("TIENDA DE ANIMALES");
            System.out.println("-------------------");

            System.out.println("1 Nuevo Perro");
            System.out.println("2 Nuevo Gato");
            System.out.println("3 Nuevo Cuyo");
            System.out.println("4 Los animales cantan");
            System.out.println("5 Que animales tienes?");
            System.out.println("6 Salir");

            System.out.println("Opcion:: ");
            int option = scan.nextInt();
            if (option > 0 && option < 7) {
                return option;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Ingresa una opcion valida");
            return -1;
        }

    }

    public static Perro createNewPerro() {
        System.out.println("Como se llama el perro?");
        String nombre = scan.next();
        System.out.println("Cual es su edad?");
        int edad = scan.nextInt();
        System.out.println("Cual es su raza");
        String raza = scan.next();
        return new Perro(nombre, edad, raza);
    }

    public static Gato createNewGato() {
        System.out.println("Como se llama el gato?");
        String nombre = scan.next();
        System.out.println("Cual es su edad?");
        int edad = scan.nextInt();

        return new Gato(nombre, edad);
    }

    public static Cuyo createNewCuyo() {
        System.out.println("Como se llama el cuyo?");
        String nombre = scan.next();
        System.out.println("Cual es su edad?");
        int edad = scan.nextInt();

        return new Cuyo(nombre, edad);
    }

    public static void main(String[] args) {

        while (true) {

            int responseMenu = mostrarMenu();

            switch (responseMenu) {

                case 1:
                    Perro perro = createNewPerro();
                    addNewAnimal(perro);
                    break;

                case 2:
                    Gato gato = createNewGato();
                    addNewAnimal(gato);
                    break;

                case 3:
                    Cuyo cuyo = createNewCuyo();
                    addNewAnimal(cuyo);
                    break;

                case 4:
                    sonidosAnimales();
                    break;

                case 5:
                    datosDeLosAnimales();
                    break;

                default:
                    System.out.println("Ninguna Opcion Seleccionada");
                    System.out.println("---------------------------");

            }

            if (responseMenu == 6) {
                break;
            }

        }
    }
}
