package com.marych.insuranceApp.menu.loginMenu;

import com.marych.insuranceApp.menu.commonCommands.ExitCommand;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TestSignInCommand {
    private SignInCommand signInCommand;
    @Mock
    private UserList userList;
    @Mock
    private MainMenu mainMenu;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        signInCommand = new SignInCommand();
    }

    @Test
    public void testWrongLogin(){
        String wrongLogin = "log";
        String password = "password";
        String input = "log\n password\nlog\n password\nlog\n password\nlog\n password";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        signInCommand.setUserList(userList);
        assertEquals(0,signInCommand.signIn());
    }
    @Test
    public void testCorrectLogin(){
        String wrongLogin = "log";
        String password = "password";
        String input = "log\n password";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        signInCommand.setUserList(userList);
        when(userList.login(wrongLogin,password)).thenReturn(new Customer(1002,"log","password"));
        assertNotEquals(0,signInCommand.signIn());
    }


    @Test
    public void testExecutionMenuWhenCorrectLogin() throws IOException {
        SignInCommand signInCommandSpy = spy(new SignInCommand());
        signInCommand.setUserList(userList);
        doReturn(1).when(signInCommandSpy).signIn();
        signInCommandSpy.setMainMenu(mainMenu);
        when(mainMenu.run()).thenReturn(true);
        signInCommandSpy.execute();
        verify(mainMenu).run();
    }

}

