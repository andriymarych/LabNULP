package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;

import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.personal.LifeInsuranceInfo;
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
public class TestCreatePersonalCommand {
    CreatePersonalCommand createPersonalCommand;

    @Mock
    InsuranceScanner insuranceScanner;

    @Mock
    InsuranceSpecialist insuranceSpecialist;

    @Mock
    InsuranceCompany insuranceCompany;

    @Mock
    InsurancePolicyMenu insurancePolicyMenu;
    @Mock
    Customer customer;

    @Mock
    CreateInsCommand createInsCommand;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "Іван \nПетренко\n1995-10-25\nм.Київ вул.Хрещатик 24\n25000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        MockitoAnnotations.openMocks(this);
        createPersonalCommand = new CreatePersonalCommand();
        createPersonalCommand.setInsuranceScanner(insuranceScanner);
        createPersonalCommand.setInsurancePolicyMenu(insurancePolicyMenu);
        createPersonalCommand.setCreateInsCommand(createInsCommand);
        when(insurancePolicyMenu.execute()).thenReturn(true);
    }


    @Test
    public void testCreateDerCommandCustomer() throws IOException {
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        insurancePolicy.setPolicyInfo(new LifeInsuranceInfo(1005));
        createPersonalCommand.setUser(customer);
        when(createInsCommand.addData(insurancePolicy,customer)).thenReturn(true);
        when(insuranceScanner.createInsurancePolicy(customer)).thenReturn(insurancePolicy);
        assertTrue(createPersonalCommand.execute());
    }

    @Test
    public void testCreateDerCommandInsuranceSpecialist() throws IOException {
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        insurancePolicy.setPolicyInfo(new LifeInsuranceInfo(1005));
        createPersonalCommand.setUser(customer);
        when(insuranceScanner.createInsurancePolicy(customer)).thenReturn(insurancePolicy);
        assertTrue(createPersonalCommand.execute());
    }
}
