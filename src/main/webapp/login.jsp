<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 06.03.18
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bonjour!</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/login" method = "POST">
    <%
        if (request.getParameter("message") != null) {
            out.println(request.getParameter("message"));
        }
    %>
    <br>
    First Name: <input type = "text" name = "login">
    <br />
    Last Name: <input type = "text" name = "password" />
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>
