<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 26/08/19
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List of Employee's Timesheets</title>
</head>
<body>
<table>
    <tr>
        <th>Week: </th>
    </tr>
    <c:forEach items="${paySlipsList}" var="paySlipDate">
        <c:url var="paySlipLink" value="/counter/showPaySlip">
            <c:param name="weekEndingDate" value="${paySlipDate}"/>
        </c:url>
        <tr>
            <td><a href="${paySlipLink}">${paySlipDate}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
