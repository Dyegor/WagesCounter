<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 9/10/19
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
<head>
    <title>Yearly Report</title>
</head>
<body>
    <label>User Id: </label>
    <table>
        <tr>
            <th>Gross Earnings</th>
            <th>Tax Paid</th>
            <th>Correct tax</th>
            <th>Tax difference</th>
        </tr>
        <tr>
            <td>${yearlyGrossEarnings}</td>
            <td>${yearlyPaye}</td>
            <td>${correctPaye}</td>
            <td>${yearlyPaye - correctPaye}</td>
        </tr>
    </table>
</body>
</html>
