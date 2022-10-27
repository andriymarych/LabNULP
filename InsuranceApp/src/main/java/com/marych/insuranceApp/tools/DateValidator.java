package com.marych.insuranceApp.tools;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$", Pattern.CASE_INSENSITIVE);

    public static String validate() {
        String email;
        Scanner in = new Scanner(System.in);
        System.out.println("Дата народження (YYYY-MM-DD):");
        for (int i = 3; i > 0; i--) {
            email = in.next();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (matcher.find()) {
                return email;
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("Ви ввели невірну дату народження" +
                            "\nУ вас залишилось " + (i - 1) + " Cпроби");
                } else {
                    System.out.println("Ви ввели невірну дату народження" +
                            "\nУ вас залишилось " + (i - 1) + " Спроба");
                }
            } else {
                System.out.println("Ви вичерпали ліміт введення адресу електронної пошти");
            }
        }
        return null;
    }
}
