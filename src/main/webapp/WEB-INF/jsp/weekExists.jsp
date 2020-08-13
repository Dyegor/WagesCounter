<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 12/08/2020
  Time: 7:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Week Already Exists</title>
</head>
<body>
<h3>Records for the period you are trying to add already exist in the database!</h3>
<form:form action="${pageContext.request.contextPath}/counter/addWeek">
    <button>Create a new Timesheet</button>
</form:form>
</body>
<button type="button" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">Back to Homepage</button>
</html>
