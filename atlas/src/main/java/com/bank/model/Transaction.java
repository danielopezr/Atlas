package com.bank.model;

import java.sql.Date;

public class Transaction {

    private int transactionID;
    private int type;
    private double amount;
    private Date transactionDate;
    private int originAccountID;
    private int destinationAccountID;

    public Transaction(int type,double amount, Date transactionDate, int originAccountID, int destinationAccountID) {
        this.transactionID = -1;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.originAccountID = originAccountID;
        this.destinationAccountID = destinationAccountID;
    }

    public Transaction() {

    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getOriginAccountID() {
        return originAccountID;
    }

    public void setOriginAccountID(int originAccountID) {
        this.originAccountID = originAccountID;
    }

    public int getDestinationAccountID() {
        return destinationAccountID;
    }

    public void setDestinationAccountID(int destinationAccountID) {
        this.destinationAccountID = destinationAccountID;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", type=" + type +
                ", transactionDate=" + transactionDate +
                ", originAccountID=" + originAccountID +
                ", destinationAccountID=" + destinationAccountID +
                '}';
    }
}
