<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 13/08/19
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<h3>Success!</h3>
<form:form action="${pageContext.request.contextPath}/counter/addWeek">
    <button>Create a new Timesheet</button>
</form:form>
<button type="button" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">Back to Homepage</button>
</body>
</html>
