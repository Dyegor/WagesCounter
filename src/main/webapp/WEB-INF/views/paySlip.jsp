<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 13/05/2020
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
<head>
    <title>Yearly Report</title>
</head>
<body>
<table>
    <tr>
        <th>Total Hours</th>
        <th>Gross Earnings</th>
        <th>Tax Paid</th>
        <th>ACC</th>
        <th>Total Earnings</th>
    </tr>
    <tr>
        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.totalHours}"/></td>
        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.grossEarnings}"/></td>
        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.paye}"/></td>
        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.accAmount}"/></td>
        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paySlip.netPay}"/></td>
    </tr>
</table>
</body>
</html>
