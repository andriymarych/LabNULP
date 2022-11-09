package com.marych.insuranceApp.menu.insuranceMenu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestInsuranceMenuCommand {

    InsuranceMenuCommand insuranceMenuCommand;
    @Mock
    InsurancePolicyMenu insurancePolicyMenu;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        insuranceMenuCommand = new InsuranceMenuCommand();
        insuranceMenuCommand.setInsurancePolicyMenu(insurancePolicyMenu);
        when(insurancePolicyMenu.execute()).thenReturn(true);
    }
    @Test
    public void testExecutionMenuCommand() throws IOException {
        assertTrue(insuranceMenuCommand.execute());
    }
}
