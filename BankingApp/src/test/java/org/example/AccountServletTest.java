package org.example;

import org.example.model.Account;
import org.example.service.AccountService;
import org.example.servlet.AccountServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServletTest {

    private AccountServlet servlet;
    private AccountService mockService;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws Exception {

        servlet = new AccountServlet();

        mockService = mock(AccountService.class);

        servlet.setAccountService(mockService);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);
    }



    @Test
    public void testDoPost_Success() throws Exception {

        when(request.getParameter("accno")).thenReturn("101");
        when(request.getParameter("name")).thenReturn("Subash");
        when(request.getParameter("balance")).thenReturn("5000");

        when(mockService.createAccount(any(Account.class))).thenReturn(true);

        servlet.doPost(request, response);

        writer.flush();

        String output = stringWriter.toString();

        assertTrue(output.contains("Account Created Successfully"));
    }



    @Test
    public void testDoPost_Failure() throws Exception {

        when(request.getParameter("accno")).thenReturn("101");
        when(request.getParameter("name")).thenReturn("Subash");
        when(request.getParameter("balance")).thenReturn("5000");

        when(mockService.createAccount(any(Account.class))).thenReturn(false);

        servlet.doPost(request, response);

        writer.flush();

        String output = stringWriter.toString();

        assertTrue(output.contains("Account Creation Failed"));
    }



    @Test
    public void testDoPost_InvalidInput() throws Exception {

        when(request.getParameter("accno")).thenReturn(null);
        when(request.getParameter("name")).thenReturn(null);
        when(request.getParameter("balance")).thenReturn(null);

        servlet.doPost(request, response);

        writer.flush();

        String output = stringWriter.toString();

        assertTrue(output.contains("Invalid Input Data"));
    }



    @Test
    public void testDoGet_FindAll() throws Exception {

        List<Account> list = new ArrayList<>();

        Account acc = new Account();
        acc.setAccNo(101);
        acc.setName("Arun");
        acc.setBalance(3000);

        list.add(acc);

        when(mockService.findAllAccounts()).thenReturn(list);

        servlet.doGet(request, response);

        writer.flush();

        String output = stringWriter.toString();

        assertTrue(output.contains("Account List"));
        assertTrue(output.contains("Arun"));
    }


    @Test
    public void testDoGet_Exception() throws Exception {

        when(mockService.findAllAccounts())
                .thenThrow(new RuntimeException("DB Error"));

        servlet.doGet(request, response);

        writer.flush();

        String output = stringWriter.toString();

        assertTrue(output.contains("Error"));
    }
}