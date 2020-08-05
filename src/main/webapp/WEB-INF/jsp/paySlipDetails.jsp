<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 25/05/2020
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
    <title>PaySlip period: ${paySlip.weekEndingDate}</title>
</head>
<body>
<h2>PaySlip period: ${paySlip.weekEndingDate}</h2>
<table>
    <tr>
        <th>Total Hours</th>
        <th>Hourly Rate</th>
        <th>Gross Earnings</th>
        <th>Tax Paid</th>
        <th>ACC</th>
        <th>Total Earnings</th>
    </tr>
    <tr>
        <td style="text-align: center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.totalHours}"/></td>
        <td style="text-align: center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.hourlyRate}"/></td>
        <td style="text-align: center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.grossEarnings}"/></td>
        <td style="text-align: center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.paye}"/></td>
        <td style="text-align: center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.accAmount}"/></td>
        <td style="text-align: center"><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.netPay}"/></td>
    </tr>
</table>
<form:form action="${pageContext.request.contextPath}/counter/showTimeSheet/${paySlip.weekEndingDate}" modelAttribute="paySlip">
    <form:hidden path="hourlyRate"/>
    <input type="submit" value="See Detailed TimeSheet"/>
</form:form>
<form:form action="${pageContext.request.contextPath}/counter/deleteWeeklyData/${paySlip.weekEndingDate}">
    <input type="submit" value="Delete WeeklyData"/>
</form:form>
<button type="button" onclick="window.location='${pageContext.request.contextPath}/index.jsp'">Back to Homepage</button>
</body>
</html>