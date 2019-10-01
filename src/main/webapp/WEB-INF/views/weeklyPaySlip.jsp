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
<c:set var="user" value="${paySlips.dailyReportsList[0].userId}"/>
<label>User Id: ${user}</label> <br> <br>
<c:set var="periodEndDate" value="${paySlips.dailyReportsList[0].weekEndingDate}"/>
<label>Period: ${periodEndDate}</label> <br> <br>
<table>
    <tr>
        <th>Day</th>
        <th>Normal Hours</th>
    </tr>
    <c:forEach items="${paySlips.dailyReportsList}" var="tempPayslip">
        <tr>
            <td>${tempPayslip.day}</td>
            <td>${tempPayslip.hoursDone}</td>
        </tr>
    </c:forEach>
</table>
<br> <input type="submit" value="Confirm"/>
</body>
</html>
