package com.marych.insuranceApp.menu.InsuranceMenu.createInsurance;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.personal.LifeInsuranceInfo;
import com.marych.insuranceApp.menu.InsuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.InsuranceScanner;

import java.io.IOException;
import java.util.Scanner;

import static com.marych.insuranceApp.Main.user;

public class CreatePersonalCommand implements MenuItem {

    InsurancePolicy insurancePolicy;

    @Override
    public void execute() throws IOException {
        createPersonalPolicy();
        InsurancePolicyMenu insurancePolicyMenu = new InsurancePolicyMenu();
        insurancePolicyMenu.execute();
    }

    private void createPersonalPolicy() throws IOException {
        InsuranceScanner insuranceScanner = new InsuranceScanner();
        if (user instanceof Customer customer) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(insuranceSpecialist);
        }
        if (insurancePolicy != null) {
            insurancePolicy.setPolicyInfo(createLifeInfo(insurancePolicy.getPolicyNo()));
            System.out.println(insurancePolicy);
            CreateInsCommand.addData(insurancePolicy);
        }
    }
    private LifeInsuranceInfo createLifeInfo(int insuranceNo) {
        Scanner in = new Scanner(System.in);
        String firstName;
        String lastName;
        String birthDate;
        String address;
        int insuredSum;
        double insuredPayment;
        System.out.println("Введіть персональні дані страхувальника");
        System.out.println("Імʼя :");
        firstName = in.next();
        System.out.println("Прізвище :");
        lastName = in.next();
        System.out.println("Дата народження (YYYY-MM-DD) : ");
        birthDate = in.next();
        in.nextLine();
        System.out.println("Адреса місця проживання : ");
        address = in.nextLine();
        double riskPercentage = CreateInsCommand.selectRisk();
        if (riskPercentage == 0) {
            return null;
        }
        System.out.println("Страхова сума: ");
        insuredSum = in.nextInt();
        insuredPayment = insuredSum * riskPercentage;
        return new LifeInsuranceInfo(insuranceNo)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setAddress(address).setRiskPercentage(riskPercentage)
                .setSumInsured(insuredSum)
                .setInsurancePayment(insuredPayment)
                .setInfoType(1);
    }
}
