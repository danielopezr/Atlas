package com.bank.controller;

import com.bank.dao.AccountDAO;
import com.bank.dao.UserDAO;
import com.bank.model.Account;
import com.bank.model.User;

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
    private UserDAO userDAO;

    public LoginServlet() {
        this.accountDAO = new AccountDAO();
        this.userDAO = new UserDAO();
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
            User user = userDAO.selectUserByID(account.getUserID());

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp");
        }
    }
}