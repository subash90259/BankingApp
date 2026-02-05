package org.example.dao;
import org.example.dbconfiguration.DbConnection;
import org.example.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

        public boolean addAccount(Account acc) {

            boolean status = false;

            try {
                Connection con = DbConnection.getConnection();

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
        public List<Account> findAllAccounts() {

            List<Account> list = new ArrayList<>();

            try {
                Connection con = DbConnection.getConnection();

                PreparedStatement ps = con.prepareStatement("select * from account");

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Account acc = new Account();

                    acc.setAccNo(rs.getInt("accno"));
                    acc.setName(rs.getString("name"));
                    acc.setBalance(rs.getDouble("balance"));

                    list.add(acc);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }
    }

