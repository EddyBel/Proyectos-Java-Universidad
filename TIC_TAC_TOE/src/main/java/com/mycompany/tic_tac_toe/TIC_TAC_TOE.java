/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tic_tac_toe;

import javax.swing.JFrame;

/**
 *
 * @author ben_9
 */
public class TIC_TAC_TOE {

    public static void main(String[] args) {
        JFrame frame = new JFrame("3 EN RAYA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(495, 540);
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setResizable(false);

        MenuInicio menu = new MenuInicio(frame);
        frame.add(menu); // Agregar el JPanel (Tablero) al JFrame
        frame.setVisible(true);

    }
}
