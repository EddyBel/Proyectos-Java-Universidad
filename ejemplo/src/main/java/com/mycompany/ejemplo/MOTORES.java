/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author ben_9
 */
public class MOTORES {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public void DimeTipoMotor (){
    
        try{
            
            String respuesta = br.readLine();
            int respuesta_numero = Integer.parseInt(respuesta);
            
            switch (respuesta_numero){
            
                case 0:
                    System.out.println("No se establece un valor para el tipo de bomba");
                    break;
                case 1:
                    System.out.println("La bomba es una bomba de agua");
                    break;
                case 2:
                    System.out.println("La bomba es una bomba de hormigon");
                    break;
                case 3:
                    System.out.println("La bomba es una bomba de gasolina");
                    break;
                case 4:
                    System.out.println("La bomba es una bomba de pasta alimenticia");
                    break;
                default:
                    System.out.println("No existe un valor valido para el");
            
            }
        
        } catch(Exception e){}
    
    }
    
}
