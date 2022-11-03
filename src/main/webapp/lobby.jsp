<%@ page import="com.example.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.UserDAO" %>
<%@ page import="com.example.SessionUtil" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lobby</title>
</head>
  <body>

    <% User user = new SessionUtil(request, response).getCurrentUser(); %>

    <% if (user == null) {
        response.sendRedirect("/index.jsp");
        return;
    } %>

    <h1><%= "Hello, " + user.getLogin() %></h1>
    <a href="logout">Logout</a>

    <p>Choose user to start conversation: </p>

    <%
      UserDAO userDAO = new UserDAO();
      List<User> users = userDAO.readAll();
    %>

    <p>
      <% for (User user1 : users) {%>
        <% if (user1.equals(user)) continue; %>
        <% String link = "\"/chat.jsp?user=" + user1.getLogin() + "\""; %>
        <a href=<%=link%>><%=user1.getLogin()%></a><br/>
      <%}%>

      <% if (users.size() <= 1) {%>
        <%= "no users found" %>
      <%}%>
    </p>

  </body>
</html>
