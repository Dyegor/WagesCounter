<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 3/05/2020
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Incorrect Input</title>
</head>
<body>
<h3>All form fields must be filled before submitting!</h3>
<form:form action="${pageContext.request.contextPath}/counter/addWeek">
    <button>Create a new Timesheet</button>
</form:form>
</body>
</html>
