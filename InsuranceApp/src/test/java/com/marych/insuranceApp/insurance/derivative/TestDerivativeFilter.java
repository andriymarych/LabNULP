package com.marych.insuranceApp.insurance.derivative;


import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.insurance.policy.property.TransportInsuranceInfo;
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
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class TestDerivativeFilter {

    DerivativeFilter derivativeFilter;
    @Mock
    Customer customer;
    @Mock
    InsuranceSpecialist insuranceSpecialist;
    @Mock
    InsuranceCompany insuranceCompany;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testDerivativeFilterWhenDerivativeExistCustomer(){
        String input = "20000 \n30000\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Customer customer = new Customer(1001,"Log","Password");
        ArrayList<InsurancePolicy> expectedFilteredList = new ArrayList<>();
        Derivative derivative = new Derivative(1001, customer, insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicyFirst = new InsurancePolicy(1005, customer, insuranceSpecialist);
        insurancePolicyFirst.setPolicyInfo(new ProfessionalActivity(1005).setSumInsured(15000));
        derivative.addPolicy(insurancePolicyFirst);
        //expectedFilteredList.add(insurancePolicyFirst);
        InsurancePolicy insurancePolicySecond = new InsurancePolicy(1010, customer, insuranceSpecialist);
        insurancePolicySecond.setPolicyInfo(new TransportInsuranceInfo(1005).setSumInsured(25000));
        derivative.addPolicy(insurancePolicySecond);
        customer.addDerivative(derivative);
        expectedFilteredList.add(insurancePolicySecond);
        derivativeFilter = new DerivativeFilter(customer,1001);
        assertEquals(expectedFilteredList,derivativeFilter.filterPrice());
    }
    @Test
    public void testDerivativeFilterWhenDerivativeExistInsuranceSpecialist(){
        String input = "20000 \n30000\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1001,"Log","Password");
        ArrayList<InsurancePolicy> expectedFilteredList = new ArrayList<>();
        Derivative derivative = new Derivative(1001, customer, insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicyFirst = new InsurancePolicy(1005, customer, insuranceSpecialist);
        insurancePolicyFirst.setPolicyInfo(new ProfessionalActivity(1005).setSumInsured(15000));
        derivative.addPolicy(insurancePolicyFirst);
        //expectedFilteredList.add(insurancePolicyFirst);
        InsurancePolicy insurancePolicySecond = new InsurancePolicy(1010, customer, insuranceSpecialist);
        insurancePolicySecond.setPolicyInfo(new TransportInsuranceInfo(1005).setSumInsured(25000));
        derivative.addPolicy(insurancePolicySecond);
        insuranceSpecialist.addDerivative(derivative);
        expectedFilteredList.add(insurancePolicySecond);
        derivativeFilter = new DerivativeFilter(insuranceSpecialist,1001);
        assertEquals(expectedFilteredList,derivativeFilter.filterPrice());
    }@Test
    public void testDerivativeFilterWhenDerivativeNotExistCustomer(){
        Customer customer = new Customer(1001,"Log","Password");
        /*ArrayList<InsurancePolicy> expectedFilteredList = new ArrayList<>();
        Derivative derivative = new Derivative(1001, customer, insuranceSpecialist)
                .setInsuranceCompany(insuranceCompany);
        InsurancePolicy insurancePolicyFirst = new InsurancePolicy(1005, customer, insuranceSpecialist);
        insurancePolicyFirst.setPolicyInfo(new ProfessionalActivity(1005).setSumInsured(15000));
        derivative.addPolicy(insurancePolicyFirst);*/
        //expectedFilteredList.add(insurancePolicyFirst);
        InsurancePolicy insurancePolicySecond = new InsurancePolicy(1010, customer, insuranceSpecialist);
        insurancePolicySecond.setPolicyInfo(new TransportInsuranceInfo(1005).setSumInsured(25000));
        derivativeFilter = new DerivativeFilter(customer,1005);
        assertNull(derivativeFilter.filterPrice());
    }
    @Test
    public void testDerivativeFilterWhenDerivativeNotExistInsuranceSpecialist(){

        InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(1001,"Log","Password");
        derivativeFilter = new DerivativeFilter(insuranceSpecialist,1001);
        assertNull(derivativeFilter.filterPrice());
    }

}
