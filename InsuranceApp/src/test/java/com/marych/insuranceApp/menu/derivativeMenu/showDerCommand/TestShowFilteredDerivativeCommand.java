package com.marych.insuranceApp.menu.derivativeMenu.showDerCommand;

import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.derivative.DerivativeFilter;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.insurance.policy.property.TransportInsuranceInfo;
import com.marych.insuranceApp.menu.derivativeMenu.DerivativeMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestShowFilteredDerivativeCommand {

    @InjectMocks
    ShowFilteredDerivativeCommand showFilteredDerivativeCommand;
    @Mock
    DerivativeMenu derivativeMenu;
    @Mock
    ShowAllDerivativesCommand showAllDerivativesCommand;
    @Mock
    DerivativeFilter derivativeFilter;
    @Mock
    InsuranceCompany insuranceCompany;

    @Mock
    Customer customer;
    @Mock
    InsuranceSpecialist insuranceSpecialist;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "1001";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        when(derivativeMenu.execute()).thenReturn(true);
    }
    @Test
    public void testShowFilteredDerivativeWhichEmptyCommand() throws IOException {
        String input = "1001";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertTrue(showFilteredDerivativeCommand.execute());
    }

    @Test
    public void testShowFilteredDerivativeListWhichCustomer() {
        String input = "1001";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ArrayList<InsurancePolicy> insurancePolicies = new ArrayList<>();
        Derivative derivative = new Derivative(1001, customer, insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005, customer, insuranceSpecialist);
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005).setSumInsured(25000));
        derivative.addPolicy(insurancePolicy);
        insurancePolicies.add(insurancePolicy);
        insurancePolicy = new InsurancePolicy(1010, customer, insuranceSpecialist);
        insurancePolicy.setPolicyInfo(new TransportInsuranceInfo(1005).setSumInsured(25000));
        derivative.addPolicy(insurancePolicy);
        insurancePolicies.add(insurancePolicy);
        customer.addDerivative(derivative);
        when(showAllDerivativesCommand.showDerivativeList()).thenReturn(true);
        when(derivativeFilter.filterPrice()).thenReturn(insurancePolicies);
        assertEquals(insurancePolicies,showFilteredDerivativeCommand.showFilteredDerivative());
    }

}
