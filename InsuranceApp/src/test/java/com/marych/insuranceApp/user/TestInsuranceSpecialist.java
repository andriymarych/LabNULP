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
public class TestInsuranceSpecialist {

    private InsuranceSpecialist insuranceSpecialist;
    @Mock
    private Customer customer;
    @Mock
    private InsurancePolicy insurancePolicy;
    @Mock
    private Derivative derivative;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        insuranceSpecialist = new InsuranceSpecialist(1003,"ivanspecvuso@gmail.com","666555");
    }
    @Test
    public void testVerifyPasswordTrue() {;
        assertTrue(insuranceSpecialist.verifyPassword("666555"));

    }
    @Test
    public void testVerifyPasswordFalse() {
        assertFalse(insuranceSpecialist.verifyPassword("656555"));
    }
    @Test
    public void testAddInsurancePolicy(){
        insurancePolicy = new InsurancePolicy(3455,customer,insuranceSpecialist);
        assertTrue(insuranceSpecialist.addInsurancePolicy(insurancePolicy));
    }
    @Test
    public void testAddNullInsurancePolicy(){
        assertFalse(insuranceSpecialist.addInsurancePolicy(null));
    }
    @Test
    public void testAddDerivative(){
        assertTrue(insuranceSpecialist.addDerivative(derivative));
    }

    @Test
    public void testAddNullDerivative(){
        assertFalse(insuranceSpecialist.addDerivative(null));
    }
    @Test
    public void testGetDerivativeWhichExist(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addDerivative(derivative);
        assertEquals(derivative,insuranceSpecialist.getDerivative(1005));
    }

    @Test
    public void testGetDerivativeWhichNotExist(){
        assertNull(insuranceSpecialist.getDerivative(5));
    }

    @Test
    public void testGetInsurancePolicyWhichExist(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        assertEquals(insurancePolicy,insuranceSpecialist.getInsurancePolicy(1005));
    }

    @Test
    public void testDeleteInsurancePolicyWhichExist(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        assertTrue(insuranceSpecialist.deleteInsurancePolicy(1005));
    }

    @Test
    public void testDeleteInsurancePolicyWhichNotExist(){
        assertFalse(insuranceSpecialist.deleteInsurancePolicy(5));
    }

    @Test
    public void testDeleteDerivativeWhichExist(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addDerivative(derivative);
        assertTrue(insuranceSpecialist.deleteDerivative(1005));
    }

    @Test
    public void testDeleteDerivativeWhichNotExist(){
        assertFalse(insuranceSpecialist.deleteDerivative(5));
    }

    @Test
    public void testGetCorrectPolicyList(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        var hashMap = new HashMap<Integer,InsurancePolicy>();
        hashMap.put(1005,insurancePolicy);
        assertEquals(hashMap,insuranceSpecialist.getInsurancePolicyList());
    }

    @Test
    public void testGetCorrectPolicyListWhenPolicyDeleted(){
        insurancePolicy = new InsurancePolicy(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        var hashMap = new HashMap<Integer,InsurancePolicy>();
        hashMap.put(1005,insurancePolicy);
        insurancePolicy = new InsurancePolicy(1006,customer,insuranceSpecialist);
        insuranceSpecialist.addInsurancePolicy(insurancePolicy);
        insuranceSpecialist.deleteInsurancePolicy(1006);
        assertEquals(hashMap,insuranceSpecialist.getInsurancePolicyList());
    }


    @Test
    public void testGetCorrectDerivativeList(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addDerivative(derivative);
        var hashMap = new HashMap<Integer,Derivative>();
        hashMap.put(1005,derivative);
        assertEquals(hashMap,insuranceSpecialist.getDerivativeList());
    }

    @Test
    public void testGetCorrectDerivativeListWhenDerivativeDeleted(){
        derivative = new Derivative(1005,customer,insuranceSpecialist);
        insuranceSpecialist.addDerivative(derivative);
        var hashMap = new HashMap<Integer,Derivative>();
        hashMap.put(1005,derivative);
        derivative = new Derivative(1006,customer,insuranceSpecialist);
        insuranceSpecialist.addDerivative(derivative);
        insuranceSpecialist.deleteDerivative(1006);
        assertEquals(hashMap,insuranceSpecialist.getDerivativeList());
    }
    @Test
    public void testGetCorrectUserRole(){
        assertEquals(1,insuranceSpecialist.getUserRole());
    }
}
