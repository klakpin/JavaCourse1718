<%@ page import="model.pojo.Training" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 07.03.18+
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
Welcome to the dashboard!<br>

<form method="post" action="training">
    <input type="hidden" name="action" value="create">
    <input type="date" name="date">
    <input type="time" name="time">
    <input type="submit" value="create">
</form>
Future trainigns are: <br>
<%
    List<Training> trainingList = (List<Training>) request.getAttribute("trainings");

    for (Training training : trainingList) {
        out.println("<a href=\"/training?id=" +training.getId() + "\">" +
                training.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd")) + "</a><br>");
    }
%>
</body>
</html>
