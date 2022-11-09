package com.marych.insuranceApp.menu;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.insuranceMenu.createInsurance.ShowInfoCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestCommandMenuExecutor {

    @Mock
    ShowInfoCommand showInfoCommand;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        String input = "show der\nshow ins";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    @Test
    public void testCommandMenuExecutorWhenCorrectCommand() throws IOException {
        Map<String, MenuItem>  menuItemMap = new HashMap<>();
        menuItemMap.put("show ins",showInfoCommand);
        when(showInfoCommand.execute()).thenReturn(true);
        assertTrue(CommandMenuExecutor.execute(menuItemMap));

    }
    @Test
    public void testCommandMenuExecutorWhenUnCorrectCommand() throws IOException {
        Map<String, MenuItem>  menuItemMap = new HashMap<>();
        menuItemMap.put("show ins",showInfoCommand);
        when(showInfoCommand.execute()).thenReturn(true);
        assertTrue(CommandMenuExecutor.execute(menuItemMap));

    }
}
