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
    <title>Payslip</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/counter/addingWeeklyPayment/${param.weekEndingDate}">
    <input type="hidden" name="totalHours" value="${paySlips.totalHours}">
    <label>Week Ending: ${paySlips.dailyReportsList[0].weekEndingDate}</label> <br> <br>
    <table>
        <tr>
            <th>Day</th>
            <th>Time In</th>
            <th>Time Out</th>
            <th>Amount of Hours</th>
        </tr>
        <c:forEach items="${paySlips.dailyReportsList}" var="tempPayslip">
            <tr>
                <td>${tempPayslip.day}</td>
                <td>${tempPayslip.startTime}</td>
                <td>${tempPayslip.finishTime}</td>
                <td>${tempPayslip.hoursDone}</td>
            </tr>
        </c:forEach>
    </table>
    <br> <input type="submit" value="Calculate weekly Payments"/>
</form:form>
<form:form action="${pageContext.request.contextPath}/counter/deleteWeeklyReport/${param.weekEndingDate}">
    <input type="submit" value="Delete Timesheet"/>
</form:form>
</body>
</html>
