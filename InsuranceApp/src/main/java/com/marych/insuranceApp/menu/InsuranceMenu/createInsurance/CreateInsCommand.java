package com.marych.insuranceApp.menu.InsuranceMenu.createInsurance;


import static com.marych.insuranceApp.Main.*;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.User.jsonScanner.JsonInfoScanner;
import com.marych.insuranceApp.User.jsonScanner.JsonScanner;
import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.menu.InsuranceMenu.InsuranceMenuCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.CommandMenuExecutor;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateInsCommand implements MenuItem {

    private final Map<String, MenuItem> menuItems;

    public CreateInsCommand() {
        menuItems = new LinkedHashMap<>();
        if (user instanceof Customer) {
            menuItems.put("liability", new CreateLiabilityCommand());
            menuItems.put("personal", new CreatePersonalCommand());
            menuItems.put("property", new CreatePropertyCommand());
            menuItems.put("info", new ShowInfoCommand());
            menuItems.put("exit", new InsuranceMenuCommand());
        } else if (user instanceof InsuranceSpecialist) {
            menuItems.put("liability", new CreateLiabilityCommand());
            menuItems.put("personal", new CreatePersonalCommand());
            menuItems.put("property", new CreatePropertyCommand());
            menuItems.put("exit", new InsuranceMenuCommand());
        }
    }

    @Override
    public void execute() {
        printMenuInfo();
        CommandMenuExecutor.execute(menuItems);
    }

    private void printMenuInfo() {
        System.out.println("*".repeat(60));
        System.out.println();
        if (user instanceof Customer) {
            System.out.println("personal - створити договір особистого страхування.");
            System.out.println("property - створити договір майнового страхування.");
            System.out.println("liability - створити договір страхування відповідальності.");
            System.out.println("info - довідка");
            System.out.println("exit - повернутися у меню керування страховими зобовʼязаннями");
        } else if (user instanceof InsuranceSpecialist) {
            System.out.println("liability - створити договір особистого страхування.");
            System.out.println("personal - створити договір майнового страхування.");
            System.out.println("property - створити договір страхування відповідальності.");
            System.out.println("exit - повернутися у меню керування страховими зобовʼязаннями");
        }
    }

    public static void addData(InsurancePolicy insurancePolicy) throws IOException {
        InsuranceCompany insuranceCompany = insurancePolicy.getInsuranceCompany();
        Customer customer = insurancePolicy.getPolicyHolder();
        InsuranceSpecialist insuranceSpecialist = insurancePolicy.getInsurer();
        insuranceCompany.addInsurancePolicy(customer, insurancePolicy);
        customer.addInsurancePolicy(insurancePolicy);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        insuranceCompany.addInsurancePolicy(customer, insurancePolicy);
        JsonInfoScanner.addPolicyInfo(insurancePolicy);
        JsonScanner.addInsurancePolicy(insurancePolicy);
    }

    public static double selectRisk() {
        Scanner in = new Scanner(System.in);
        int percentage;
        System.out.println("Введіть рівень ризику ( 0 - 100 %) :");
        for (int i = 3; i > 0; i--) {
            percentage = in.nextInt();
            if (percentage > 0 && percentage < 90) {
                return percentage / 100.0;
            }
            if (i != 1) {
                if (i == 3) {
                    System.out.println("Ви ввели невірний рівень ризику." +
                            "\nУ вас залишилось " + (i - 1) + " Cпроби");
                } else {
                    System.out.println("Ви ввели невірний рівень ризику." +
                            "\nУ вас залишилось " + (i - 1) + " Спроба");
                }
            } else {
                System.out.println("Ви вичерпали ліміт введення рівня ризику.");
            }
        }
        return 0;
    }


}
