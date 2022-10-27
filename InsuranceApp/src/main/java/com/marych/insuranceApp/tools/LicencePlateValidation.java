package com.marych.insuranceApp.tools;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LicencePlateValidation {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z]{2}\\d{4}[A-Z]{2}$");

    public static String validate() {
        String licensePlate ;
        Scanner in = new Scanner(System.in);
        System.out.println("Державний номерний знак транспортного засобу (використовувати англійські літери) :");
        for (int i = 3; i > 0; i--) {
            licensePlate = in.next();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(licensePlate);
            if (matcher.find()) {
                return licensePlate;
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("Ви ввели невірний державний номерний знак." +
                            "\nУ вас залишилось " + (i-1) + " Cпроби");
                } else {
                    System.out.println("Ви ввели невірний державний номерний знак." +
                            "\nУ вас залишилось " + (i-1) + " Спроба");
                }
            } else {
                System.out.println("Ви вичерпали ліміт введення державного номерного знаку");
            }
        }
        return null;
    }

}
