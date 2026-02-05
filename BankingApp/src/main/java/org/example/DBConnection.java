package org.example;

import org.example.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBConnection {



    protected Connection getConnection() {
            return Banking.getConnection();
        }

    public boolean addAccount(Account acc) {

        boolean status = false;

        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "insert into account(accno, name, balance) values(?,?,?)"
            );

            ps.setInt(1, acc.getAccNo());
            ps.setString(2, acc.getName());
            ps.setDouble(3, acc.getBalance());

            int result = ps.executeUpdate();

            if (result > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    }



