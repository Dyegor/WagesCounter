<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 9/10/19
  Time: 7:33 PM
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
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paymentSummary.totalHours}"/></td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paymentSummary.grossEarnings}"/></td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paymentSummary.paye}"/></td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paymentSummary.accAmount}"/></td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${paymentSummary.netPay}"/></td>
        </tr>
    </table>
</body>
</html>