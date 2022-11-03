<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
    <body>

        <% String error = request.getParameter("error"); %>
        <% if (error != null) {%>
            <%= error %>
            <a href="auth-form.jsp"> - login</a><br/>
            <br/>
        <%}%>

        <form action="/registration-after">
            Login: <input type="text" name="login"><br>
            Password: <input type="text" name="password"><br>
            <input type="submit" value="Register">
        </form>

    </body>
</html>
