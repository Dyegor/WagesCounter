<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 28/09/19
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>TimeSheet period: ${timeSheet.weekEndingDate}</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/counter/updateWeeklyData/" modelAttribute="timeSheet">
    <label>Week Ending: ${timeSheet.weekEndingDate}</label> <br> <br>
    <form:input type="hidden" path="weekEndingDate"/>
    <label>Hourly Rate: </label>
    <form:input type="text" path="hourlyRate"/>
    <table>
        <tr>
            <th>Day</th>
            <th>Time In</th>
            <th>Time Out</th>
            <th>Amount of Hours</th>
        </tr>
        <c:forEach items="${timeSheet.dailyReportsList}" var="tempTimeSheet" varStatus="loop">
            <tr>
                <td><c:out value="${tempTimeSheet.day}"/><form:input type="hidden" path="dailyReportsList[${loop.index}].day"
                                value="${tempTimeSheet.day}"/></td>
                <td><form:input type="time" path="dailyReportsList[${loop.index}].startTime"
                                value="${tempTimeSheet.startTime}" step="1800" pattern="[0-9:]*"/></td>
                <td><form:input type="time" path="dailyReportsList[${loop.index}].finishTime"
                                value="${tempTimeSheet.finishTime}" step="1800" size="10" pattern="[0-9:]*"/></td>
                <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${tempTimeSheet.hoursDone}"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Update Weekly Data"/>
</form:form>
<form:form action="${pageContext.request.contextPath}/counter/deleteWeeklyData/${param.weekEndingDate}">
    <input type="submit" value="Delete Weekly Data"/>
</form:form>
<button type="button" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">Back to Homepage</button>
</body>
</html>
