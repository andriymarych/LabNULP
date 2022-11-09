package com.marych.insuranceApp.menu.insuranceMenu;

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



import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestDeleteInsCommand {
    DeleteInsCommand deleteInsCommand;
    @Mock
    InsurancePolicyMenu insurancePolicyMenu;
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
        deleteInsCommand = new DeleteInsCommand();
        deleteInsCommand.setInsurancePolicyMenu(insurancePolicyMenu);
        deleteInsCommand.setJsonScanner(jsonScanner);
        when(insurancePolicyMenu.execute()).thenReturn(true);

    }
    @Test
    public void deleteWhenExistInsurancePolicyCustomer(){
        String input = "1005";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = new Customer(1005,"log","password");
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        ProfessionalActivity professionalActivity = new ProfessionalActivity(1005);
        insurancePolicy.setPolicyInfo(professionalActivity);
        customer.addInsurancePolicy(insurancePolicy);
        deleteInsCommand.setUser(customer);
        assertTrue(deleteInsCommand.execute());
    }
    @Test
    public void deleteWhenExistInsurancePolicyInsuranceSpecialist(){
        String input = "1005";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1005,"log","password");
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        ProfessionalActivity professionalActivity = new ProfessionalActivity(1005);
        insurancePolicy.setPolicyInfo(professionalActivity);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        deleteInsCommand.setUser(insuranceSpecialist);
        assertTrue(deleteInsCommand.execute());
    }
    @Test
    public void deleteWhenNotExistInsurancePolicyCustomer(){
        String input = "1010\n 1015";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = new Customer(1005,"log","password");
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        ProfessionalActivity professionalActivity = new ProfessionalActivity(1005);
        insurancePolicy.setPolicyInfo(professionalActivity);
        customer.addInsurancePolicy(insurancePolicy);
        deleteInsCommand.setUser(customer);
        assertFalse(deleteInsCommand.execute());
    }

    @Test
    public void deleteWhenNotExistInsurancePolicyInsuranceSpecialist(){
        String input = "1010\n 1015";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1005,"log","password");
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        ProfessionalActivity professionalActivity = new ProfessionalActivity(1005);
        insurancePolicy.setPolicyInfo(professionalActivity);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        deleteInsCommand.setUser(insuranceSpecialist);
        assertFalse(deleteInsCommand.execute());
    }
}
