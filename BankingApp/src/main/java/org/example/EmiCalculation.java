package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

@WebServlet("/emi")
public class EmiCalculation extends HttpServlet {
Scanner sc=new Scanner(System.in);

    static double compute_emi (double p,int year,double annualrate)
    {
       int n=year*12;
       double r= annualrate / (12*100);
       return (p * r * Math.pow(1+r,n) )/ (Math.pow(1+r, n) - 1);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        int accno=Integer.parseInt(req.getParameter("accno"));
        String holderName=req.getParameter("name");
        double loan_amount=Double.parseDouble(req.getParameter("loan_amount"));
        int year=Integer.parseInt(req.getParameter("year"));
        double rate=Double.parseDouble(req.getParameter("rate"));
        PrintWriter out = response.getWriter();

        try {
            double emi=compute_emi(loan_amount,year,rate);
            out.println("Account number"+accno);
            out.println("Account_holder name"+holderName);
            out.println("Your loan amount"+" "+"$"+loan_amount);
            out.println("year"+ year);
            out.println("monthly emi"+Math.round(emi));

        } catch (Exception  e) {
            throw new RuntimeException(e);
        }

    }
}