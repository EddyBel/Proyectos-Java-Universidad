/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.testingapp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author ben_9
 */
public class TestingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        
        
        System.out.println("Como te llamas? ");
        String nameInput = scanner.nextLine();
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Boolean validateBirthday = false;
        LocalDate birthday = null;
        
        while (!validateBirthday) {
            System.out.println("Cual es tu edad en dia/mes/año? ");
            String birthdayInput = scanner.nextLine();
            
            try {
                birthday = LocalDate.parse(birthdayInput, formatter);
                validateBirthday = true;
            } catch (DateTimeParseException e) {
                System.out.println("Necesitas una fecha valida dia/mes/año");
            }
        }
        
        person.setName(nameInput);
        person.setBithDay(birthday);
        
        person.Gretting();
        person.CalculateAge();
        person.Congratulation();
    }
}
