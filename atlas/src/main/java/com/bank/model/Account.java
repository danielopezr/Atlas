package com.bank.model;

import java.sql.Date;

public class Account {
    
    private int accountID;
    private int number;
    private String username;
    private String password;
    private int type;
    private double balance;
    private Date openDate;
    private Date cancelDate;
    private int status;
    private int userID;

    private static int lastNumber = 1;

    public Account(String username, String password, int type, double balance, Date openDate,
                        int status, int userID) {
        this.accountID = -1;
        this.number = ++lastNumber;
        this.username = username;
        this.password = password;
        this.type = type;
        this.balance = balance;
        this.openDate = openDate;
        this.cancelDate = new Date(0);
        this.status = status;
        this.userID = userID;
    }

    public Account() {

    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "accountID=" + accountID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", balance='" + balance + '\'' +
                ", openDate=" + openDate +
                ", cancelDate=" + cancelDate +
                ", status=" + status +
                ", userID=" + userID +
                '}';
    }
}
