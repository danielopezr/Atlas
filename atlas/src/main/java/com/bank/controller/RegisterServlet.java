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
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    private AccountDAO accountDAO;

    public RegisterServlet() {
        this.userDAO = new UserDAO();
        this.accountDAO = new AccountDAO();
    }

    //GET, UPDATE, POST, DELETE son métodos HTTP
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName1 = request.getParameter("firstName");
        String lastName2 = request.getParameter("middleName");
        
        String birthDateStr = request.getParameter("birthDate");
        LocalDate localDate = LocalDate.parse(birthDateStr);
        Date birthDate = Date.valueOf(localDate);

        String adress = request.getParameter("adress");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String identification = request.getParameter("identification");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String typeStr = request.getParameter("type");
        int type = 0;
        if (typeStr != null && !typeStr.isEmpty()) {
            type = Integer.parseInt(typeStr);
        } else {
            request.setAttribute("message", "You need to choose a plan");
            request.getRequestDispatcher("plans.jsp").forward(request, response);
        }

        if (userDAO.userExists(identification)) {
            request.setAttribute("message", "There is already a record with the provided identification. If you don't recognize this record, please contact your nearest branch for assistance.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (accountDAO.userNameInUse(username)) {
            request.setAttribute("message", "The selected username is in used, please try other one");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            User newUser = new User(firstName, middleName, lastName1, lastName2, birthDate, adress, phoneNumber, email, identification);
            userDAO.insertUser(newUser);
            int userID = userDAO.getUserByIdentification(identification);

            LocalDate today = LocalDate.now();
            Date openDate = Date.valueOf(today);

            Account newAccount = new Account(username, password, type, 0, openDate, 1, userID);
            accountDAO.insertAccount(newAccount);

            HttpSession session = request.getSession();
            session.setAttribute("registering", newUser);
            response.sendRedirect("login.jsp");
        }
    }
}