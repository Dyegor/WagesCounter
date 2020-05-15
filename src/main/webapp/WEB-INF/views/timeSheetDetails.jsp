<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 28/09/19
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>TimeSheet period: ${timeSheet.weekEndingDate}</title>
</head>
<body>
<label>Week Ending: ${timeSheet.weekEndingDate}</label> <br> <br>
<table>
    <tr>
        <th>Day</th>
        <th>Time In</th>
        <th>Time Out</th>
        <th>Amount of Hours</th>
    </tr>
    <c:forEach items="${timeSheet.dailyReportsList}" var="tempTimeSheet">
        <tr>
            <td style="text-align: center">${tempTimeSheet.day}</td>
            <td style="text-align: center">${tempTimeSheet.startTime}</td>
            <td style="text-align: center">${tempTimeSheet.finishTime}</td>
            <td style="text-align: center">${tempTimeSheet.hoursDone}</td>
        </tr>
    </c:forEach>
</table>
<form:form action="${pageContext.request.contextPath}/counter/deleteTimeSheet/${param.weekEndingDate}">
    <input type="submit" value="Delete TimeSheet"/>
</form:form>
</body>
</html>
