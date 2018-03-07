<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.pojo.Training" %><%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 07.03.18
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Training</title>
</head>
<body>

This is page of training
<br />
<%
    Training training = (Training) request.getAttribute("training");
    out.print("Traininig will be at " + training.getDateTime());
%>
<br />

<%
    int trainingRating = training.getRating();
    if (trainingRating != -1) {
        out.print("Training rating is " + trainingRating + "<br />");
    }
%>
<form action="${pageContext.request.contextPath}/training" method="post">

    <input type="hidden" name="action" value="rate">
    <input type="hidden" value="<%out.print(training.getId());%>" name="id">
    <input type="number" name="rating" min="0" max="10">
    <input type="submit" value="submit">
</form>

<form action="<c:url value="/training"/>" method="post">
    <input type="hidden" name="action" value="delete">
    <input type="hidden" value="<%out.print(training.getId());%>" name="id">
    <input type="submit" value="delete!">
</form>
<%

%>
</body>
</html>
