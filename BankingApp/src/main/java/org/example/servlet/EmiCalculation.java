package org.example.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class EmiCalculation extends HttpServlet {
    private static final Logger log= LoggerFactory.getLogger(EmiCalculation.class);
Scanner sc=new Scanner(System.in);

    static double compute_emi (double p,int year,double annualrate)
    {
       int n=year*12;
       double r= annualrate / (12*100);
       return (p * r * Math.pow(1+r,n) )/ (Math.pow(1+r, n) - 1);
    }
    @Override
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
            log.info("Account number"+accno);
            log.info("Account_holder name"+holderName);
            log.info("Your loan amount"+" "+"$"+loan_amount);
           log.info("year"+ year);
            log.info("monthly emi"+Math.round(emi));

        } catch (Exception  e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {
            int accno = Integer.parseInt(req.getParameter("accno"));
            String holderName = req.getParameter("name");
            double loan_amount = Double.parseDouble(req.getParameter("loan_amount"));
            int year = Integer.parseInt(req.getParameter("year"));
            double rate = Double.parseDouble(req.getParameter("rate"));

            double emi = compute_emi(loan_amount, year, rate);

            log.info("Account number: " + accno);
            log.info("Account holder name: " + holderName);
            log.info("Loan amount: " + loan_amount);
            log.info("Year: " + year);
            log.info("Monthly EMI: " + Math.round(emi));

            out.println("<html><body>");
            out.println("<h2>EMI Calculation Result</h2>");
            out.println("<p>Account Number: " + accno + "</p>");
            out.println("<p>Account Holder: " + holderName + "</p>");
            out.println("<p>Loan Amount: " + loan_amount + "</p>");
            out.println("<p>Year: " + year + "</p>");
            out.println("<p>Interest Rate: " + rate + "</p>");
            out.println("<h3>Monthly EMI: " + Math.round(emi) + "</h3>");
            out.println("</body></html>");

        } catch (Exception e) {
            log.error("Error in EMI Calculation", e);
            out.println("Invalid Input Data");
        }
    }
}