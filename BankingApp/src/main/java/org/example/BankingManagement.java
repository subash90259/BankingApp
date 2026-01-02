package org.example;

import org.example.Banking;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankingManagement {






    public boolean addAccount(Account acc) {

            boolean status = false;

            try {
                Connection con = Banking.getConnection();

                String sql = "INSERT INTO bankingapplication VALUES (?, ?, ? )";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(  1, acc.getAccNo());
                ps.setString(2, acc.getName());
                ps.setDouble(3, acc.getBalance());

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    status = true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return status;
        }



    }

