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

@WebServlet("/hello")
    public class AccountServlet extends HttpServlet {
    double initial =1000.0;
//
        BankingManagement bankingManagement = new BankingManagement();

        protected void doPost(HttpServletRequest req, HttpServletResponse response)
                throws ServletException, IOException {

//             response.setContentType("text/html");
//             PrintWriter out=response.getWriter();
//             out.println("<h1>Hello, this is my sevlet code</h
            String accno=req.getParameter("accno");
            String namestr=req.getParameter("name");
            String balance=req.getParameter("balance");
            PrintWriter out=response.getWriter();
            if (accno != null && namestr != null && balance != null)
            {
                out.println("Account created");
            }
             else {
                 out.println("account not created");
            }

            int accNo = Integer.parseInt(accno);
            String name =namestr;
            double bal = Double.parseDouble(balance);
            out.println("Account details");
            out.println("account number"+" "+accno+" " +" account holder name"+" "+name+" "+"balance"+" "+bal);

                bankingManagement.addAccount(new Account(accNo, name, bal));



        }

        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            try {
                Connection con = Banking.getConnection();

                if(con==null){
                    throw new RuntimeException("Connection not established");
                }
                String sql = "SELECT * FROM  bankingapplication";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                out.println("<h2>Bank Accounts</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>Acc No</th><th>Name</th><th>Balance</th></tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("accno") + "</td>");
                    out.println("<td>" + rs.getString("name") + "</td>");
                    out.println("<td>" + rs.getDouble("balance") + "</td>");
                     out.println("</tr>");
                }

                out.println("</table>");


                con.close();

            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }
        }
    }



