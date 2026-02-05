package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.example.model.Account;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class BankingManagementTest {


    @Test
    void testAddAccount_success() throws Exception {

        DBConnection management = new DBConnection();

        Account acc = new Account();
        acc.setAccNo(101);
        acc.setName("Subash");
        acc.setBalance(5000);

        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(1);   // success insert

        try (MockedStatic<Banking> bankingMock = mockStatic(Banking.class)) {

            bankingMock.when(Banking::getConnection).thenReturn(con);

            boolean result = management.addAccount(acc);

            assertTrue(result);
        }
    }


    @Test
    void testAddAccount_failure() throws Exception {

        DBConnection management = new DBConnection();

        Account acc = new Account();
        acc.setAccNo(102);
        acc.setName("Test");
        acc.setBalance(0);

        Connection con = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeUpdate()).thenReturn(0);   // insert failed

        try (MockedStatic<Banking> bankingMock = mockStatic(Banking.class)) {

            bankingMock.when(Banking::getConnection).thenReturn(con);

            boolean result = management.addAccount(acc);

            assertFalse(result);
        }
    }

    @Test
    void testAddAccount_exception() throws Exception {

        DBConnection management = new DBConnection();

        Account acc = new Account();
        acc.setAccNo(103);
        acc.setName("ErrorUser");
        acc.setBalance(100);

        try (MockedStatic<Banking> bankingMock = mockStatic(Banking.class)) {

            bankingMock.when(Banking::getConnection)
                    .thenThrow(new RuntimeException("DB error"));

            boolean result = management.addAccount(acc);

            assertFalse(result);
        }
    }
}