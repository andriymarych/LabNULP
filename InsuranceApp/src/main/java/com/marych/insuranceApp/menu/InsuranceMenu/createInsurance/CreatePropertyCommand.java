package com.marych.insuranceApp.menu.InsuranceMenu.createInsurance;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.insurance.policy.property.TransportInsuranceInfo;
import com.marych.insuranceApp.menu.InsuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.InsuranceScanner;
import com.marych.insuranceApp.tools.LicencePlateValidation;

import java.io.IOException;
import java.util.Scanner;

import static com.marych.insuranceApp.Main.user;

public class CreatePropertyCommand implements MenuItem {
    InsurancePolicy insurancePolicy;

    @Override
    public void execute() throws IOException {
        createPropertyPolicy();
        InsurancePolicyMenu insurancePolicyMenu = new InsurancePolicyMenu();
        insurancePolicyMenu.execute();
    }

    private void createPropertyPolicy() throws IOException {
        InsuranceScanner insuranceScanner = new InsuranceScanner();
        if (user instanceof Customer customer) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(insuranceSpecialist);
        }
        if (insurancePolicy != null) {
            insurancePolicy.setPolicyInfo(createTransportInsurance(insurancePolicy.getPolicyNo()));
            System.out.println(insurancePolicy);
            CreateInsCommand.addData(insurancePolicy);
        }
    }
    private TransportInsuranceInfo createTransportInsurance(int policyNo) {
        Scanner in = new Scanner(System.in);
        String ownerFirstName;
        String ownerLastName;
        String carBrand;
        String carModel;
        String licensePlate;
        int insuredSum;
        double insuredPayment;
        System.out.println("Введіть персональні дані страхувальника");
        System.out.println("Імʼя :");
        ownerFirstName = in.next();
        System.out.println("Прізвище :");
        ownerLastName = in.next();
        in.nextLine();
        System.out.println("Марка авто : ");
        carBrand = in.nextLine();
        System.out.println("Модель авто : ");
        carModel = in.nextLine();
        licensePlate = LicencePlateValidation.validate();
        double riskPercentage = 0.03;
        System.out.println("Страхова сума: ");
        insuredSum = in.nextInt();
        insuredPayment = insuredSum * riskPercentage;
        return new TransportInsuranceInfo(policyNo)
                .setOwnerFirstName(ownerFirstName)
                .setOwnerLastName(ownerLastName)
                .setCarBrand(carBrand)
                .setCarModel(carModel)
                .setLicensePlateNumber(licensePlate)
                .setSumInsured(insuredSum)
                .setInsurancePayment(insuredPayment)
                .setInfoType(3)
                .setRiskPercentage(riskPercentage);
    }
}
