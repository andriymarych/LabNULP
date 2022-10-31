package com.marych.insuranceApp.menu.InsuranceMenu.createInsurance;


import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.menu.InsuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.InsuranceScanner;

import java.io.IOException;
import java.util.Scanner;

import static com.marych.insuranceApp.Main.user;

public class CreateLiabilityCommand implements MenuItem {
    InsurancePolicy insurancePolicy;

    @Override
    public void execute() throws IOException {
        createLiabilityPolicy();
        InsurancePolicyMenu insurancePolicyMenu = new InsurancePolicyMenu();
        insurancePolicyMenu.execute();
    }
    private void createLiabilityPolicy() throws IOException {
        InsuranceScanner insuranceScanner = new InsuranceScanner();
        if (user instanceof Customer customer) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(insuranceSpecialist);
        }
        if (insurancePolicy != null) {
            insurancePolicy.setPolicyInfo(createProfessionalActivityInfo(insurancePolicy.getPolicyNo()));
            System.out.println(insurancePolicy);
            CreateInsCommand.addData(insurancePolicy);
        }
    }
    private ProfessionalActivity createProfessionalActivityInfo(int policyNo) {
        Scanner in = new Scanner(System.in);
        String firstName;
        String lastName;
        String professionalActivity;
        String position;
        String companyName;
        int insuredSum;
        double insuredPayment;
        System.out.println("Введіть персональні дані страхувальника");
        System.out.println("Імʼя :");
        firstName = in.next();
        System.out.println("Прізвище :");
        lastName = in.next();
        in.nextLine();
        System.out.println("Сфера професійної діяльності : ");
        professionalActivity = in.nextLine();
        System.out.println("Назва підприємства, установи, організації: ");
        companyName = in.nextLine();
        System.out.println("Посада : ");
        position = in.nextLine();
        double riskPercentage = CreateInsCommand.selectRisk();
        if (riskPercentage == 0) {
            return null;
        }
        System.out.println("Страхова сума: ");
        insuredSum = in.nextInt();
        insuredPayment = insuredSum * riskPercentage;
        return new ProfessionalActivity(policyNo)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setProfessionalActivity(professionalActivity)
                .setCompanyName(companyName)
                .setPosition(position)
                .setRiskPercentage(riskPercentage)
                .setSumInsured(insuredSum)
                .setInsurancePayment(insuredPayment)
                .setInfoType(2)
                ;
    }
}
