package com.marych.insuranceApp.menu.DerivativeMenu;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.User.jsonScanner.JsonScanner;
import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.menu.InsuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.DerivativeScanner;

import java.io.IOException;

import static com.marych.insuranceApp.Main.user;

public class CreateDerCommand implements MenuItem {
    Derivative derivative;
    @Override
    public void execute() throws IOException {
        createDerivative();
        DerivativeMenu derivativeMenu = new DerivativeMenu();
        derivativeMenu.execute();
    }

    private void createDerivative() throws IOException {
        DerivativeScanner derivativeScanner = new DerivativeScanner();
        if (user instanceof Customer customer) {
            derivative = derivativeScanner.createDerivative(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            derivative = derivativeScanner.createDerivative(insuranceSpecialist);
        }
        if (derivative != null) {
            derivative.showDerivative();
            addData();
        }
    }
    public  void addData() throws IOException {
        InsuranceCompany insuranceCompany = derivative.getInsuranceCompany();
        Customer customer = derivative.getDerivativeHolder();
        InsuranceSpecialist insuranceSpecialist = derivative.getInsurer();
        insuranceCompany.addDerivative(customer, derivative);
        customer.addDerivative(derivative);
        insuranceSpecialist.addDerivative(derivative);
        insuranceCompany.addDerivative(customer, derivative);
        JsonScanner.addDerivative(derivative);
    }
}
