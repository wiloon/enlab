<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>

<shiro:guest>
    Hi there! Please <a href="login.jsp">Login</a> or <a href="signup.jsp">Signup</a> today!
</shiro:guest>
<shiro:user>
Welcome back John! Not John? Click <a href="login.jsp">here<a> to login.
    </shiro:user>
    <form action="login.action" method="post">

        Username: <input type="text" name="userName"/> <br/>
        Password: <input type="password" name="password"/>

        <input type="checkbox" name="rememberMe" value="true">Remember Me?</input>

        <input type="submit" value="Submit"/>
    </form>
</body>
</html>