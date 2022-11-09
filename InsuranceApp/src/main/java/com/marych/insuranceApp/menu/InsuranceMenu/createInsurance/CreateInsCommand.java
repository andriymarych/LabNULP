package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;


import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.menu.CommandMenuExecutor;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.insuranceMenu.InsuranceMenuCommand;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;
import com.marych.insuranceApp.tools.UserLogger;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.scanners.jsonScanner.JsonInfoScanner;
import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.user.User;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class CreateInsCommand implements MenuItem {

    private final Map<String, MenuItem> menuItems;
    private JsonInfoScanner jsonInfoScanner;
    private JsonScanner jsonScanner;

    public CreateInsCommand() {
        menuItems = new LinkedHashMap<>();
        if (Main.user instanceof Customer) {
            menuItems.put("liability", new CreateLiabilityCommand());
            menuItems.put("personal", new CreatePersonalCommand());
            menuItems.put("property", new CreatePropertyCommand());
            menuItems.put("info", new com.marych.insuranceApp.menu.insuranceMenu.createInsurance.ShowInfoCommand());
            menuItems.put("exit", new InsuranceMenuCommand());
        } else if (Main.user instanceof InsuranceSpecialist) {
            menuItems.put("liability", new CreateLiabilityCommand());
            menuItems.put("personal", new CreatePersonalCommand());
            menuItems.put("property", new CreatePropertyCommand());
            menuItems.put("exit", new InsuranceMenuCommand());
        }
    }

    @Override
    public boolean execute() {
        printMenuInfo();
        CommandMenuExecutor.execute(menuItems);
        return true;
    }

    private void printMenuInfo() {
        System.out.println("*".repeat(60));
        System.out.println();
        if (Main.user instanceof Customer) {
            System.out.println("personal - створити договір особистого страхування.");
            System.out.println("property - створити договір майнового страхування.");
            System.out.println("liability - створити договір страхування відповідальності.");
            System.out.println("info - довідка");
            System.out.println("exit - повернутися у меню керування страховими зобовʼязаннями");
        } else if (Main.user instanceof InsuranceSpecialist) {
            System.out.println("liability - створити договір особистого страхування.");
            System.out.println("personal - створити договір майнового страхування.");
            System.out.println("property - створити договір страхування відповідальності.");
            System.out.println("exit - повернутися у меню керування страховими зобовʼязаннями");
        }
    }

    public boolean addData(InsurancePolicy insurancePolicy, User user) throws IOException {
        UserLogger userLogger = new UserLogger();
        userLogger.info("User id" + user.getUserId() + " created insurance policy No" + insurancePolicy.getPolicyNo());
        jsonInfoScanner = getJsonInfoScanner();
        jsonScanner = getJsonScanner();
        InsuranceCompany insuranceCompany = insurancePolicy.getInsuranceCompany();
        Customer customer = insurancePolicy.getPolicyHolder();
        InsuranceSpecialist insuranceSpecialist = insurancePolicy.getInsurer();
        insuranceCompany.addInsurancePolicy(customer, insurancePolicy);
        customer.addInsurancePolicy(insurancePolicy);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        insuranceCompany.addInsurancePolicy(customer, insurancePolicy);
        jsonInfoScanner.addPolicyInfo(insurancePolicy);
        jsonScanner.addInsurancePolicy(insurancePolicy);
        return true;
    }
    public void setJsonInfoScanner(JsonInfoScanner jsonInfoScanner){
        this.jsonInfoScanner = jsonInfoScanner;
    }
    public JsonInfoScanner getJsonInfoScanner(){
        return Objects.requireNonNullElseGet(jsonInfoScanner, JsonInfoScanner::new);
    }
    public void setJsonScanner(JsonScanner jsonInfoScanner){
        this.jsonScanner = jsonInfoScanner;
    }
    public JsonScanner getJsonScanner(){
        return Objects.requireNonNullElseGet(jsonScanner, JsonScanner::new);
    }



}
