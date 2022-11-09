package com.marych.insuranceApp.menu.derivativeMenu;

import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.scanners.DerivativeScanner;
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
import java.io.IOException;
import java.io.InputStream;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TestCreateDerCommand {
    CreateDerCommand createDerCommand;

    @Mock
    DerivativeScanner derivativeScanner;

    @Mock
    InsuranceSpecialist insuranceSpecialist;

    @Mock
    InsuranceCompany insuranceCompany;

    @Mock
    JsonScanner jsonScanner;
    @Mock
    DerivativeMenu derivativeMenu;
    @Mock
    Customer customer;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "Іван \nПетренко\nМедицина\nОбласна Стоматологічна Поліклініка\nСтоматолог\n20000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        createDerCommand = new CreateDerCommand();
        createDerCommand.setDerivativeScanner(derivativeScanner);
        createDerCommand.setJsonScanner(jsonScanner);
        createDerCommand.setDerivativeMenu(derivativeMenu);
        when(derivativeMenu.execute()).thenReturn(true);
    }

    @Test
    public void testCreateDerCommandCustomer() throws IOException {
        Derivative derivative = new Derivative(1001,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005));
        derivative.addPolicy(insurancePolicy);
        createDerCommand.setUser(customer);
        when(derivativeScanner.createDerivative(customer)).thenReturn(derivative);
        assertTrue(createDerCommand.execute());
    }

    @Test
    public void testCreateDerCommandInsuranceSpecialist() throws IOException {
        Derivative derivative = new Derivative(1001,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005));
        derivative.addPolicy(insurancePolicy);
        createDerCommand.setUser(insuranceSpecialist);
        assertTrue(createDerCommand.execute());
    }

}
