package org.example.servlet;

import org.example.model.Account;
import org.example.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class AccountServlet extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountService();
    }

    // For JUnit Testing Purpose
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            String accno = req.getParameter("accno");
            String name = req.getParameter("name");
            String balance = req.getParameter("balance");

            if (accno == null || name == null || balance == null) {
                out.println("Invalid Input Data");
                return;
            }

            int accountNo = Integer.parseInt(accno);
            double bal = Double.parseDouble(balance);

            Account account = new Account();
            account.setAccNo(accountNo);
            account.setName(name);
            account.setBalance(bal);

            boolean result = accountService.createAccount(account);

            if (result) {
                out.println("Account Created Successfully");
            } else {
                out.println("Account Creation Failed");
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            List<Account> accounts = accountService.findAllAccounts();

            out.println("<html><body>");
            out.println("<h2>Account List</h2>");

            out.println("<table border='1'>");
            out.println("<tr><th>Account No</th><th>Name</th><th>Balance</th></tr>");

            for (Account acc : accounts) {
                out.println("<tr>");
                out.println("<td>" + acc.getAccNo() + "</td>");
                out.println("<td>" + acc.getName() + "</td>");
                out.println("<td>" + acc.getBalance() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}