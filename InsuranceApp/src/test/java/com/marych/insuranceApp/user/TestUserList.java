package com.marych.insuranceApp.user;


import com.marych.insuranceApp.diiaGov.DiiaGov;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;
import com.marych.insuranceApp.tools.LogStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.marych.insuranceApp.scanners.UserScanner;

import java.io.*;


import static com.marych.insuranceApp.Main.diiaGovDocuments;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TestUserList {
    private UserList userList;

    @Mock
    User user;

    @Mock
    JsonScanner jsonScanner;
    @Mock
    UserScanner userScanner;


    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userList = new UserList();
    }

    @Test
    public void testUserLoginWrongLogin() {
        String wrongLogin = "wrongLog";
        String password = "password";
        assertNull(userList.login(wrongLogin, password));
    }

    @Test
    public void testUserLoginWrongPassword() {
        String wrongLogin = "Log";
        String password = "password";
        userList.addUserLogin("Log", user);
        when(user.verifyPassword("password")).thenReturn(false);
        when(user.getLogStatus()).thenReturn(LogStatus.PASSWORD);
        assertEquals(LogStatus.PASSWORD,userList.login(wrongLogin, password).getLogStatus());
    }

    @Test
    public void testUserLoginCorrectLoginAndPassword() {
        String wrongLogin = "Log";
        String password = "password";
        userList.addUserLogin("Log", user);
        when(user.verifyPassword(password)).thenReturn(true);
        assertNotNull(userList.login(wrongLogin, password));
    }

    @Test
    public void testUserCreatingWrongDiia() throws IOException {
        assertNull(userList.userCreate(777));
    }

    @Test
    public void testUserCreatingCorrectDiia() throws IOException {
        String input = "1111";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int documentNo = 5;
        DiiaGov diiaGov = new DiiaGov(documentNo, 1111)
                .setFirstName("Сергій")
                .setLastName("Підлужний")
                .setITN(44531)
                .setBirthDate("2000-05-25");
        diiaGovDocuments.addIdDocument(diiaGov);
        userList = new UserList(userScanner);
        userList.setJsonScanner(jsonScanner);
        when(userScanner.createEmail()).thenReturn("email@email.com");
        when(userScanner.createLogin()).thenReturn("log");
        when(userScanner.createPassword()).thenReturn("password");
        assertNotNull(userList.userCreate(documentNo));
    }
    @Test
    public void testUserCreatingIncorrectEmail() throws IOException {
        String input = "1111";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int documentNo = 5;
        DiiaGov diiaGov = new DiiaGov(documentNo, 1111)
                .setFirstName("Сергій")
                .setLastName("Підлужний")
                .setITN(44531)
                .setBirthDate("2000-05-25");
        diiaGovDocuments.addIdDocument(diiaGov);
        userList = new UserList(userScanner);
        assertNull(userList.userCreate(documentNo));
    }
    @Test
    public void testAddUserEmail(){
        String userEmail = "test133@gmail.com";
        userList.addUserEmail(userEmail,user);
        assertTrue(userList.getUserEmails().containsKey(userEmail));
        assertEquals(user,userList.getUserEmails().get(userEmail));
    }
    @Test
    public void testAddUserId(){
        userList.addUserId(777,user);
        assertTrue(userList.getUserIdList().containsKey(777));
        assertEquals(user,userList.getUserIdList().get(777));
    }

    @Test
    public void testGetCorrectEmailList(){
        userList.addUserEmail("email@gmail.com",user);
        assertEquals(1,userList.getUserEmails().size());
        userList.addUserEmail("emai2@gmail.com",user);
        assertEquals(2,userList.getUserEmails().size());
    }

    @Test
    public void testGetCorrectLoginList(){
        userList.addUserLogin("email@gmail.com",user);
        assertEquals(1,userList.getUserLogins().size());
        userList.addUserLogin("login",user);
        assertEquals(2,userList.getUserLogins().size());
    }




}
