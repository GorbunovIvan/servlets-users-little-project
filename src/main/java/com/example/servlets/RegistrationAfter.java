package com.example.servlets;

import java.io.*;

import com.example.SessionUtil;
import com.example.User;
import com.example.UserDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/registration-after")
public class RegistrationAfter extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));

        try {
            UserDAO userDAO = new UserDAO();
            userDAO.create(user);
        } catch (Exception e) {
            String error = e.getMessage();
            response.sendRedirect("/registration-form.jsp?error=" + error);
            return;
        }

        new SessionUtil(request, response).createSession(user);

        response.sendRedirect("/lobby.jsp");

    }
}