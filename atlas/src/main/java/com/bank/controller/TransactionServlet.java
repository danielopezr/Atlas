package com.bank.controller;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import com.bank.model.Account;
import com.bank.model.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    public TransactionServlet() {
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
    }

    //GET, UPDATE, POST, DELETE son métodos HTTP
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        CurrencyManager currencyManager = new CurrencyManager();

        String typeStr = request.getParameter("type");
        int type = Integer.parseInt(typeStr);

        Account account = (Account) session.getAttribute("account");
        String amountStr = request.getParameter("amount");
        double amount = Double.parseDouble(amountStr);
        String currency = request.getParameter("currency");
        String password = request.getParameter("password");
        double amountPesos = currencyManager.convertToPesos(amount, currency);
        double newBalance;
        double newBalanceDestination;

        String destinationAccountNumberStr = request.getParameter("number");
        Account destinationAccount = account;
        int destinationAccountNumber = -1;
        if (type == 2 && destinationAccountNumberStr != null && !destinationAccountNumberStr.isEmpty()) {
            destinationAccountNumber = Integer.parseInt(destinationAccountNumberStr);
            destinationAccount = accountDAO.selectAccountByNumber(destinationAccountNumber); 
        }

        if (!password.equals(account.getPassword())) {
            request.setAttribute("message", "Wrong password");
            request.getRequestDispatcher("transfer.jsp").forward(request, response);
        }

        switch (type) {
            case 1:
                newBalance = account.getBalance() + amountPesos;
                try {
                    accountDAO.updateBalance(account.getNumber(), newBalance);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                if (!accountDAO.accountExistsByNumber(destinationAccountNumber)) {
                    request.setAttribute("message", "No account");
                    request.getRequestDispatcher("transfer.jsp").forward(request, response);
                } else if (account.getBalance() < amountPesos) {
                    request.setAttribute("message", "No money");
                    request.getRequestDispatcher("transfer.jsp").forward(request, response);
                } else {
                    newBalance = account.getBalance() - amountPesos;
                    try {
                        accountDAO.updateBalance(account.getNumber(), newBalance);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    newBalanceDestination = destinationAccount.getBalance() + amountPesos;
                    try {
                        accountDAO.updateBalance(destinationAccountNumber, newBalanceDestination);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                if (account.getBalance() < amountPesos) {
                    request.setAttribute("message", "No money");
                    request.getRequestDispatcher("withdrawal.jsp").forward(request, response);
                } else {
                    newBalance = account.getBalance() - amountPesos;
                    try {
                        accountDAO.updateBalance(account.getNumber(), newBalance);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                System.out.println("Opción no válida");
        }

        LocalDate today = LocalDate.now();
        Date transactionDate = Date.valueOf(today);

        Transaction transaction = new Transaction(type, amountPesos, transactionDate, account.getAccountID(), destinationAccount.getAccountID());
        transactionDAO.insertTransaction(transaction);
        response.sendRedirect("index.jsp");
    }
}