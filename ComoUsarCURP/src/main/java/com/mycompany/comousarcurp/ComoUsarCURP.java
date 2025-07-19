/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.comousarcurp;

/**
 *
 * @author ben_9
 */
public class ComoUsarCURP {

    public static void main(String[] args) {
        CURP generador = new CURP();
        String curp = generador.generarCURP("Eduardo Antonio", "Rangel", "Serrano", "2002-07-01", "MÉXICO", "H");
        System.out.println("Tu CURP es::: " + curp);
    }
}
