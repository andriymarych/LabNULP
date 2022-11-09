package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;


import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.property.TransportInsuranceInfo;
import com.marych.insuranceApp.menu.insuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.marych.insuranceApp.scanners.InsuranceScanner;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCreatePropertyCommand {
    CreatePropertyCommand createPropertyCommand;

    @Mock
    InsuranceScanner insuranceScanner;

    @Mock
    InsuranceSpecialist insuranceSpecialist;
    @Mock
    InsurancePolicyMenu insurancePolicyMenu;
    @Mock
    Customer customer;
    @Mock
    CreateInsCommand createInsCommand;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "Іван \nПетренко\nBMW\n5-series\nKA1007AK\n20000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        createPropertyCommand = new CreatePropertyCommand();
        createPropertyCommand.setInsuranceScanner(insuranceScanner);
        createPropertyCommand.setCreateInsCommand(createInsCommand);
        createPropertyCommand.setInsurancePolicyMenu(insurancePolicyMenu);
        when(insurancePolicyMenu.execute()).thenReturn(true);
    }

    @Test
    public void testPropertyInsuranceCommandWhenCustomerCreate() throws IOException {
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(new InsuranceCompany(901, "Вусо", "Київ" ));;
        insurancePolicy.setPolicyInfo(new TransportInsuranceInfo(1005));
        createPropertyCommand.setUser(customer);
        when(insuranceScanner.createInsurancePolicy(customer)).thenReturn(insurancePolicy);
        assertTrue(createPropertyCommand.execute());
    }
    @Test
    public void testPropertyInsuranceCommandWhenInsuranceSpecialistCreate() throws IOException {
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(new InsuranceCompany(901, "Вусо", "Київ" ));;
        insurancePolicy.setPolicyInfo(new TransportInsuranceInfo(1005));
        createPropertyCommand.setUser(insuranceSpecialist);
        when(createInsCommand.addData(insurancePolicy,insuranceSpecialist)).thenReturn(true);
        when(insuranceScanner.createInsurancePolicy(insuranceSpecialist)).thenReturn(insurancePolicy);
        assertTrue(createPropertyCommand.execute());
    }
}
