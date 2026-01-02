package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Banking {
    static Connection con;

   static String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bankingapplication";
    static String user = "root";
    static String pass = "root";

    public static Connection getConnection() {
        try {
            Class.forName(mysqlJDBCDriver);
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Connection Failed! " + e.getMessage());
        }
        return con;
    }
}
