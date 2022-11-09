package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;


import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.insuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.scanners.InsuranceScanner;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;


import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class CreateLiabilityCommand implements MenuItem {
    InsurancePolicy insurancePolicy;

    InsurancePolicyMenu insurancePolicyMenu;
    User user ;
    InsuranceScanner insuranceScanner;
    CreateInsCommand createInsCommand;
    @Override
    public boolean execute() throws IOException {
        user = getUser();
        insurancePolicyMenu = getInsurancePolicyMenu();
        insuranceScanner = getInsuranceScanner();
        createInsCommand = getCreateInsCommand();
        createLiabilityPolicy();
        insurancePolicyMenu.execute();
        return true;
    }
    private void createLiabilityPolicy() throws IOException {
        if (user instanceof Customer customer) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicy = insuranceScanner.createInsurancePolicy(insuranceSpecialist);
        }
        if (insurancePolicy != null) {
            insurancePolicy.setPolicyInfo(createProfessionalActivityInfo(insurancePolicy.getPolicyNo()));
            System.out.println(insurancePolicy);
            createInsCommand.addData(insurancePolicy,user);
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
        double riskPercentage = 0.1;
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
    public void setInsurancePolicyMenu(InsurancePolicyMenu insurancePolicyMenu){
        this.insurancePolicyMenu = insurancePolicyMenu;
    }
    public InsurancePolicyMenu getInsurancePolicyMenu(){
        return Objects.requireNonNullElseGet(insurancePolicyMenu, InsurancePolicyMenu::new);
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
    public void setCreateInsCommand(CreateInsCommand createInsCommand){
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
