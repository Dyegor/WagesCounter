<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 12/08/19
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
<head>
    <title>TimeSheet</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/counter/addingWeeklyData" modelAttribute="weeklyTimeSheet">
    <label>Week Ending: </label>
    <form:input type="date" path="weekEndingDate"/>
    <label>Hourly Rate : </label>
    <form:input type="text" path="hourlyRate"/>
    <table>
        <tr>
            <th>Day</th>
            <th>Time In</th>
            <th>Time Out</th>
        </tr>
        <c:forEach items="${weeklyTimeSheet.dailyReportsList}" var="tempDailyReportsList" varStatus="loop">
        <tr>
            <td><c:out value="${tempDailyReportsList.day}"/></td>
            <td><form:input type="time" path="dailyReportsList[${loop.index}].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[${loop.index}].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
        </c:forEach>
    </table>
    <br> <input type="submit" value="Save Weekly Data"/>
</form:form>
<button type="button" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">Back to Homepage</button>
</body>
</html>