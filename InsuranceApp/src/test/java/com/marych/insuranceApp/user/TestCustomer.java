package com.marych.insuranceApp.user;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestCustomer {

    private Customer customer;

    @Mock
    private InsuranceSpecialist insuranceSpecialist;
    @Mock
    private InsurancePolicy insurancePolicy;
    @Mock
    private Derivative derivative;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(1002,"sergiy2003@gmail.com","666555");
    }


    @Test
    public void testVerifyPasswordTrue() {;
        assertTrue(customer.verifyPassword("666555"));

    }
    @Test
    public void testVerifyPasswordFalse() {
        assertFalse(customer.verifyPassword("656555"));
    }
    @Test
    public void testAddInsurancePolicy(){
        insurancePolicy = new InsurancePolicy(3455,customer,insuranceSpecialist);
        assertTrue(customer.addInsurancePolicy(insurancePolicy));
    }
    @Test
    public void testAddNullInsurancePolicy(){
        assertFalse(customer.addInsurancePolicy(null));
    }
    @Test
    public void testAddDerivative(){
        assertTrue(customer.addDerivative(derivative));
    }

    @Test
    public void testAddNullDerivative(){
        assertFalse(customer.addDerivative(null));
    }
    @Test
    public void testGetDerivativeWhichExist(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        customer.addDerivative(derivative);
        assertEquals(derivative,customer.getDerivative(1005));
    }

    @Test
    public void testGetDerivativeWhichNotExist(){
        assertNull(customer.getDerivative(5));
    }

    @Test
    public void testGetInsurancePolicyWhichExist(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        customer.addInsurancePolicy(insurancePolicy);
        assertEquals(insurancePolicy,customer.getInsurancePolicy(1005));
    }

    @Test
    public void testDeleteInsurancePolicyWhichExist(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        customer.addInsurancePolicy(insurancePolicy);
        assertTrue(customer.deleteInsurancePolicy(1005));
    }

    @Test
    public void testDeleteInsurancePolicyWhichNotExist(){
        assertFalse(customer.deleteInsurancePolicy(5));
    }

    @Test
    public void testDeleteDerivativeWhichExist(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        customer.addDerivative(derivative);
        assertTrue(customer.deleteDerivative(1005));
    }

    @Test
    public void testDeleteDerivativeWhichNotExist(){
        assertFalse(customer.deleteDerivative(5));
    }

    @Test
    public void testGetCorrectPolicyList(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        customer.addInsurancePolicy(insurancePolicy);
        var hashMap = new HashMap<Integer,InsurancePolicy>();
        hashMap.put(1005,insurancePolicy);
        assertEquals(hashMap,customer.getInsurancePolicyList());
    }

    @Test
    public void testGetCorrectPolicyListWhenPolicyDeleted(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        customer.addInsurancePolicy(insurancePolicy);
        var hashMap = new HashMap<Integer,InsurancePolicy>();
        hashMap.put(1005,insurancePolicy);
        insurancePolicy = new InsurancePolicy(1006,customer,insuranceSpecialist);
        customer.addInsurancePolicy(insurancePolicy);
        customer.deleteInsurancePolicy(1006);
        assertEquals(hashMap,customer.getInsurancePolicyList());
    }


    @Test
    public void testGetCorrectDerivativeList(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        customer.addDerivative(derivative);
        var hashMap = new HashMap<Integer,Derivative>();
        hashMap.put(1005,derivative);
        assertEquals(hashMap,customer.getDerivativeList());
    }

    @Test
    public void testGetCorrectDerivativeListWhenDerivativeDeleted(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        customer.addDerivative(derivative);
        var hashMap = new HashMap<Integer,Derivative>();
        hashMap.put(1005,derivative);
        derivative = new Derivative(1006,customer,insuranceSpecialist);
        customer.addDerivative(derivative);
        customer.deleteDerivative(1006);
        assertEquals(hashMap,customer.getDerivativeList());
    }
    @Test
    public void testGetCorrectUserRole(){
        assertEquals(0,customer.getUserRole());
    }


}
