package com.bank.controller;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AccountDAO accountDAO;

    public LoginServlet() {
        this.accountDAO = new AccountDAO();
    }

    //GET, UPDATE, POST, DELETE son métodos HTTP
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = accountDAO.selectAccountByUsername(username);

        if (account == null || !password.equals(account.getPassword())) {
            request.setAttribute("message", "Wrong username or password");
            System.out.println("Contraseña incorrecta");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            response.sendRedirect("index.jsp");
        }
    }
}