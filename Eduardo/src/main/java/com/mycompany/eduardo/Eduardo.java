/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
*/

package com.mycompany.eduardo;

import java.util.Scanner;
import com.mycompany.eduardo.MySQL;

/**
 *
 * @author ben_9
 */
public class Eduardo {

    // private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        MySQL mysql = new MySQL();
        mysql.connection();

        // // Definir variables
        // String nombre;
        // String apellidoPaterno;
        // String apellidoMaterno;
        // int fechaActual;
        // int fechaNacimiento;

        // // Capturar datos de usuario
        // System.out.println("Como te llamas?");
        // nombre = scan.nextLine();

        // System.out.println("Cual es tu apellido paterno?");
        // apellidoPaterno = scan.nextLine();

        // System.out.println("Cual es tu apellido materno?");
        // apellidoMaterno = scan.nextLine();

        // System.out.println("En que año naciste?");
        // fechaNacimiento = scan.nextInt();

        // System.out.println("Que año es actualmente?");
        // fechaActual = scan.nextInt();

        // scan.close();

        // // Mostrar los datos
        // System.out.println("Hola!!! " + (nombre + " " + apellidoPaterno + " " +
        // apellidoMaterno));
        // System.out.println("Tu edad es: " + (fechaActual - fechaNacimiento));
    }
}
