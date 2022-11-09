package com.marych.insuranceApp.menu.derivativeMenu.showDerCommand;


import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.menu.derivativeMenu.DerivativeMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestShowAllDerivativesCommand {
    ShowAllDerivativesCommand showAllDerivativesCommand;
    @Mock
    Customer customer;
    @Mock
    InsuranceSpecialist insuranceSpecialist;
    @Mock
    DerivativeMenu derivativeMenu;
    @Mock
    InsuranceCompany insuranceCompany;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "Іван \nПетренко\nМедицина\nОбласна Стоматологічна Поліклініка\nСтоматолог\n20000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        showAllDerivativesCommand = new ShowAllDerivativesCommand();
        showAllDerivativesCommand.setDerivativeMenu(derivativeMenu);
        when(derivativeMenu.execute()).thenReturn(true);
    }

    @Test
    public void testShowAllDerivativeWhenDerivativeListExistCustomer() throws IOException {
        Derivative derivative = new Derivative(1001,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005));
        derivative.addPolicy(insurancePolicy);
        var derivativeList = new HashMap<Integer,Derivative>();
        derivativeList.put(1001,derivative);
        showAllDerivativesCommand.setUser(customer);
        when(customer.getDerivativeList()).thenReturn(derivativeList);
        assertTrue(showAllDerivativesCommand.execute());
    }
    @Test
    public void testShowAllDerivativeWhenDerivativeListExistInsuranceSpecialist() throws IOException {
        Derivative derivative = new Derivative(1001,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005));
        derivative.addPolicy(insurancePolicy);
        var derivativeList = new HashMap<Integer,Derivative>();
        derivativeList.put(1001,derivative);
        showAllDerivativesCommand.setUser(insuranceSpecialist);
        when(insuranceSpecialist.getDerivativeList()).thenReturn(derivativeList);
        assertTrue(showAllDerivativesCommand.execute());
    }
    @Test
    public void testShowAllDerivativeWhenDerivativeListNotExistCustomer() throws IOException {
        var derivativeList = new HashMap<Integer,Derivative>();
        showAllDerivativesCommand.setUser(customer);
        when(customer.getDerivativeList()).thenReturn(null);
        when(customer.getDerivativeList()).thenReturn(derivativeList);
        assertFalse(showAllDerivativesCommand.execute());
    }
    @Test
    public void testShowAllDerivativeWhenDerivativeListNotExistInsuranceSpecialist() throws IOException {
        var derivativeList = new HashMap<Integer,Derivative>();
        showAllDerivativesCommand.setUser(insuranceSpecialist);
        when(insuranceSpecialist.getDerivativeList()).thenReturn(null);
        when(insuranceSpecialist.getDerivativeList()).thenReturn(derivativeList);
        assertFalse(showAllDerivativesCommand.execute());
    }

   /* @Test
    public void testShowAllDerivativeWhenDerivativesAreNotExistInsuranceSpecialist() throws IOException {
        Derivative derivative = new Derivative(1001,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005));
        derivative.addPolicy(insurancePolicy);
        createDerCommand.setUser(customer);
        when(derivativeScanner.createDerivative(customer)).thenReturn(derivative);
        assertTrue(createDerCommand.execute());
    }*/

}
