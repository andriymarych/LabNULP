package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;

import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.marych.insuranceApp.scanners.jsonScanner.JsonInfoScanner;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCreateInsCommand {
    private CreateInsCommand createInsCommand;

    @Mock
    InsuranceSpecialist insuranceSpecialist;

    @Mock
    InsuranceCompany insuranceCompany;

    @Mock
    Customer customer;

    @Mock
    JsonScanner jsonScanner;

    @Mock
    JsonInfoScanner jsonInfoScanner;



    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "Іван \nПетренко\nМедицина\nОбласна Стоматологічна Поліклініка\nСтоматолог\n20000";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        createInsCommand = new CreateInsCommand();
    }
    @Test
    public void TestAddData() throws IOException {
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        ProfessionalActivity professionalActivity = new ProfessionalActivity(1005);
        insurancePolicy.setPolicyInfo(professionalActivity);
        createInsCommand.setJsonScanner(jsonScanner);
        createInsCommand.setJsonInfoScanner(jsonInfoScanner);
        when(jsonScanner.addInsurancePolicy(insurancePolicy)).thenReturn(true);
        when(jsonInfoScanner.addPolicyInfo(insurancePolicy)).thenReturn(true);
        assertTrue(createInsCommand.addData(insurancePolicy,customer));
    }
}
