package org.example;

public class Account {
    private int accno;
    private String name;
    private double balance;

    public int getAccNo() {
        return accno;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccNo(int accNo) {
        this.accno = accNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(int acno, String name, double balance) {
        this.accno = acno;
        this.name = name;
        this.balance = balance;
    }

    public int getAcno() {
        return accno;
    }
}