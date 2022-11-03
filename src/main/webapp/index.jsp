<%@ page import="com.example.User" %>
<%@ page import="com.example.SessionUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello</title>
</head>
    <body>

        <% User user = new SessionUtil(request, response).getCurrentUser(); %>

        <% if (user == null) { %>
            <h1><%= "Login or register" %></h1>
            <br/>
            <a href="auth-form.jsp">Login</a><br/>
            <a href="registration-form.jsp">Registration</a><br/>
        <% } else { %>
            <h1><%= "Hello, " + user.getLogin() %></h1>
            <a href="logout">Logout</a>
        <%}%>

    </body>
</html>