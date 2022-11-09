package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.property.TransportInsuranceInfo;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.insuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.tools.LicencePlateValidation;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.scanners.InsuranceScanner;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;


import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class CreatePropertyCommand implements MenuItem {
    InsurancePolicy insurancePolicy;
    CreateInsCommand createInsCommand;
    User user;
    InsuranceScanner insuranceScanner;
    InsurancePolicyMenu insurancePolicyMenu;

    @Override
    public boolean execute() throws IOException {
        user = getUser();
        insurancePolicyMenu = getInsurancePolicyMenu();
        createInsCommand = getCreateInsCommand();
        insuranceScanner = getInsuranceScanner();
        createPropertyPolicy();
        insurancePolicyMenu.execute();
        return true;
    }

    private void createPropertyPolicy() throws IOException {
        if (user instanceof Customer customer) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(insuranceSpecialist);
        }
        if (insurancePolicy != null) {
            insurancePolicy.setPolicyInfo(createTransportInsurance(insurancePolicy.getPolicyNo()));
            System.out.println(insurancePolicy);
            createInsCommand.addData(insurancePolicy,user);
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
        licensePlate = LicencePlateValidation.validate(in);
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
    public void setUser(User user){
        this.user = user;
    }
    public User getUser() {
        if (Main.user != null) {
            return Main.user;
        } else {
            return user;
        }
    }
    public void setInsurancePolicyMenu(InsurancePolicyMenu insurancePolicyMenu){
        this.insurancePolicyMenu = insurancePolicyMenu;
    }
    public InsurancePolicyMenu getInsurancePolicyMenu(){
        return Objects.requireNonNullElseGet(insurancePolicyMenu, InsurancePolicyMenu::new);
    }
    public void setCreateInsCommand(CreateInsCommand createInsCommand) {
        this.createInsCommand = createInsCommand;
    }

    public CreateInsCommand getCreateInsCommand() {
        return Objects.requireNonNullElseGet(createInsCommand, CreateInsCommand::new);
    }
    public void setInsuranceScanner(InsuranceScanner insuranceScanner){
        this.insuranceScanner = insuranceScanner;
    }
    public InsuranceScanner getInsuranceScanner(){
        return Objects.requireNonNullElseGet(insuranceScanner, InsuranceScanner::new);
    }
}
