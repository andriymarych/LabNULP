package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;

import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.menu.insuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.scanners.InsuranceScanner;
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
public class TestCreateLiabilityCommand {
    CreateLiabilityCommand createLiabilityCommand;

    @Mock
    InsuranceScanner insuranceScanner;

    @Mock
    InsuranceSpecialist insuranceSpecialist;

    @Mock
    InsuranceCompany insuranceCompany;

    @Mock
    JsonScanner jsonScanner;
    @Mock
    InsurancePolicyMenu insurancePolicyMenu;
    @Mock
    Customer customer;

    @Mock
    CreateInsCommand createInsCommand;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "Іван \nПетренко\nМедицина\nОбласна Стоматологічна Поліклініка\nСтоматолог\n20000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        createLiabilityCommand = new CreateLiabilityCommand();
        createLiabilityCommand.setInsuranceScanner(insuranceScanner);
        createLiabilityCommand.setCreateInsCommand(createInsCommand);
        createLiabilityCommand.setInsurancePolicyMenu(insurancePolicyMenu);
        when(insurancePolicyMenu.execute()).thenReturn(true);

    }

    @Test

    public void testLiabilityCommandWhenCustomerCreate() throws IOException {
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(new InsuranceCompany(901, "Вусо", "Київ" ));
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005));
        createLiabilityCommand.setUser(customer);
        when(createInsCommand.addData(insurancePolicy,customer)).thenReturn(true);
        when(insuranceScanner.createInsurancePolicy(customer)).thenReturn(insurancePolicy);
        assertTrue(createLiabilityCommand.execute());
    }
    @Test
    public void testLiabilityCommandWhenInsuranceSpecialistCreate() throws IOException {
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(new InsuranceCompany(901, "Вусо", "Київ" ));
        insurancePolicy.setPolicyInfo(new ProfessionalActivity(1005));
        createLiabilityCommand.setUser(insuranceSpecialist);
        when(createInsCommand.addData(insurancePolicy,insuranceSpecialist)).thenReturn(true);
        when(insuranceScanner.createInsurancePolicy(insuranceSpecialist)).thenReturn(insurancePolicy);
        assertTrue(createLiabilityCommand.execute());
    }
}
