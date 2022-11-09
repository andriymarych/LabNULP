package com.marych.insuranceApp.menu.insuranceMenu;


import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestShowInsCommand {
    ShowInsCommand showInsCommand;

    @Mock
    InsuranceSpecialist insuranceSpecialist;
    @Mock
    Customer customer;
    @Mock
    InsuranceCompany insuranceCompany;
    @Mock
    InsurancePolicyMenu insurancePolicyMenu;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        showInsCommand  = new ShowInsCommand();
        showInsCommand.setInsurancePolicyMenu(insurancePolicyMenu);
        when(insurancePolicyMenu.execute()).thenReturn(true);
    }
    @Test
    public void testShowInsuranceListCustomer(){
        Customer customer = new Customer(1005,"log","password");
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        ProfessionalActivity professionalActivity = new ProfessionalActivity(1005);
        insurancePolicy.setPolicyInfo(professionalActivity);
        customer.addInsurancePolicy(insurancePolicy);
        showInsCommand.setUser(customer);
        assertTrue(showInsCommand.execute());
    }
    @Test
    public void testShowInsuranceListInsuranceSpecialist(){
        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1005,"log","password");
        InsurancePolicy insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        ProfessionalActivity professionalActivity = new ProfessionalActivity(1005);
        insurancePolicy.setPolicyInfo(professionalActivity);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        showInsCommand.setUser(insuranceSpecialist);
        assertTrue(showInsCommand.execute());
    }
    @Test
    public void testShowInsuranceListWhichIsEmptyCustomer(){
        Customer customer = new Customer(1005,"log","password");
        showInsCommand.setUser(customer);
        assertFalse(showInsCommand.execute());
    }
    @Test
    public void testShowInsuranceListWhichIsEmptyInsuranceSpecialist(){
        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1005,"log","password");
        showInsCommand.setUser(insuranceSpecialist);
        assertFalse(showInsCommand.execute());
    }
}
