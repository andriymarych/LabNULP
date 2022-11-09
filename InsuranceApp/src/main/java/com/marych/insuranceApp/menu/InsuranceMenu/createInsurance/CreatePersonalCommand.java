package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.personal.LifeInsuranceInfo;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.insuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.scanners.InsuranceScanner;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class CreatePersonalCommand implements MenuItem {

    InsurancePolicy insurancePolicy;
    CreateInsCommand createInsCommand;
    User user;
    InsuranceScanner insuranceScanner;
    InsurancePolicyMenu insurancePolicyMenu;

    @Override
    public boolean execute() throws IOException {
        user = getUser();
        createInsCommand = getCreateInsCommand();
        insurancePolicyMenu = getInsurancePolicyMenu();
        createInsCommand = getCreateInsCommand();
        insuranceScanner = getInsuranceScanner();
        createPersonalPolicy();
        insurancePolicyMenu = getInsurancePolicyMenu();
        insurancePolicyMenu.execute();
        return true;
    }

    private void createPersonalPolicy() throws IOException {
        if (user instanceof Customer customer) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(insuranceSpecialist);
        }
        if (insurancePolicy != null) {
            insurancePolicy.setPolicyInfo(createLifeInfo(insurancePolicy.getPolicyNo()));
            System.out.println(insurancePolicy);
            createInsCommand.addData(insurancePolicy,user);
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
        double riskPercentage = 0.04;
        System.out.println("Страхова сума: ");
        insuredSum = in.nextInt();
        insuredPayment = insuredSum * riskPercentage;
        return new LifeInsuranceInfo(insuranceNo)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setAddress(address)
                .setRiskPercentage(riskPercentage)
                .setSumInsured(insuredSum)
                .setInsurancePayment(insuredPayment)
                .setInfoType(1);
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
