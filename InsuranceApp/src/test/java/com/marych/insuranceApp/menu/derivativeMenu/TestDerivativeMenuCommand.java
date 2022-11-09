package com.marych.insuranceApp.menu.derivativeMenu;

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
public class TestDerivativeMenuCommand {

    DerivativeMenuCommand derivativeMenuCommand;
    @Mock
    DerivativeMenu derivativeMenu;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        derivativeMenuCommand = new DerivativeMenuCommand();
        derivativeMenuCommand.setDerivativeMenu(derivativeMenu);
        when(derivativeMenu.execute()).thenReturn(true);
    }
    @Test
    public void testExecutionMenuCommand() throws IOException {
        assertTrue(derivativeMenuCommand.execute());
    }
}
