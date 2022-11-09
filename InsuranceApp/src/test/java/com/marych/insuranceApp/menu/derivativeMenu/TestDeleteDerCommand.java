package com.marych.insuranceApp.menu.derivativeMenu;

import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestDeleteDerCommand {
    DeleteDerCommand deleteDerCommand;
    @Mock
    DerivativeMenu derivativeMenu;
    @Mock
    InsuranceSpecialist insuranceSpecialist;
    @Mock
    Customer customer;
    @Mock
    InsuranceCompany insuranceCompany;
    @Mock
    JsonScanner jsonScanner;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        deleteDerCommand = new DeleteDerCommand();
        deleteDerCommand.setDerivativeMenuMenu(derivativeMenu);
        deleteDerCommand.setJsonScanner(jsonScanner);
        when(derivativeMenu.execute()).thenReturn(true);
       // when(jsonScanner.(new InsurancePolicy())).thenReturn(true);
    }
    @Test
    public void deleteExistInsurancePolicyCustomer(){
        String input = "1005";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = new Customer(1005,"log","password");
        Derivative derivative = new Derivative(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        customer.addDerivative(derivative);
        deleteDerCommand.setUser(customer);
        assertTrue(deleteDerCommand.execute());
    }
    @Test
    public void deleteWhenExistInsurancePolicyInsuranceSpecialist(){
        String input = "1005";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1005,"log","password");
        Derivative derivative = new Derivative(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        insuranceSpecialist.addDerivative(derivative);
        deleteDerCommand.setUser(insuranceSpecialist);
        assertTrue(deleteDerCommand.execute());
    }

    @Test
    public void deleteWhenNotExistInsurancePolicyInsuranceSpecialist(){
        String input = "1010\n1015";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1005,"log","password");
        Derivative derivative = new Derivative(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        insuranceSpecialist.addDerivative(derivative);
        deleteDerCommand.setUser(insuranceSpecialist);
        assertFalse(deleteDerCommand.execute());
    }
    @Test
    public void deleteIfExistInsurancePolicyCustomer(){
        String input = "1010\n 1015";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = new Customer(1005,"log","password");
        Derivative derivative = new Derivative(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        customer.addDerivative(derivative);
        deleteDerCommand.setUser(customer);
        assertFalse(deleteDerCommand.execute());
    }
}
