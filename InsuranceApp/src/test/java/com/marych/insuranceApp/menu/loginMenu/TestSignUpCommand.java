package com.marych.insuranceApp.menu.loginMenu;


import com.marych.insuranceApp.menu.mainMenu.MainMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.UserList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestSignUpCommand {
    private SignUpCommand signUpCommand;
    @Mock
    UserList userList;

    @Mock
    MainMenu mainMenu;



    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        signUpCommand = new SignUpCommand();
    }

    @Test
    public void testExecutionWhenWrongDiiaDocument() throws IOException {
        String input = "7777";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        signUpCommand.setUserList(userList);
        signUpCommand.setMainMenu(mainMenu);
        when(userList.userCreate(7777)).thenReturn(null);
        assertFalse(signUpCommand.execute());
    }
    @Test
    public void testExecutionWhenCorrectDiiaDocument() throws IOException {
        SignUpCommand signUpCommandSpy = spy(new SignUpCommand());
        String input = "7777";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        signUpCommandSpy.setUserList(userList);
        signUpCommandSpy.setMainMenu(mainMenu);
        when(userList.userCreate(7777)).thenReturn(new Customer(1001,"log","password"));
        when(mainMenu.run()).thenReturn(true);
        signUpCommandSpy.execute();
    }
}
