package com.marych.insuranceApp.menu.insuranceMenu.createInsurance;

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
public class TestShowInfoCommand {
    ShowInfoCommand showInfoCommand;
    @Mock
    CreateInsCommand createInsCommand;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        showInfoCommand = new ShowInfoCommand();
        showInfoCommand.setCreateInsCommand(createInsCommand);
        when(createInsCommand.execute()).thenReturn(true);
    }

    @Test
    public void testShowInfoCommand() throws IOException {
        assertTrue(showInfoCommand.execute());
    }
}
