/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testingapp;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author ben_9
 */
public class Person {
    
    /* Variables para capturar los datos */
    private String name;
    private LocalDate birthday;
    
    public void setName (String namePerson) {
        this.name = namePerson;
    }
    
    public void setBithDay (LocalDate birthdayPerson) {
        this.birthday = birthdayPerson;
    }
    
    public String Gretting () {
      String gretting = "Hola! " + name;
      System.out.println(gretting);
      return gretting;
    }
    
    public int CalculateAge () {
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthday, now);
        System.out.println("Tienes " + age.getYears() + "años");
        return age.getYears();
    }
    
    public Boolean Congratulation () {
        LocalDate now = LocalDate.now();
        
        if (now == birthday) {
            System.out.println("Feliz cumpleanos " + name);
            return true;
        } else {
            System.out.println("Upps! Aun no es tu cumpleanos " + name);
            return false;
        }
    }
}
