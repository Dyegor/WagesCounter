<%--
  Created by IntelliJ IDEA.
  User: dmitriy
  Date: 12/08/19
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css"/>
<head>
    <title>TimeSheet</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/counter/addingWeekHours">
    <label>Week Ending: </label>
    <form:input type="date" path="weekEndingDate"/>
    <table>
        <tr>
            <th>Day</th>
            <th>Time In</th>
            <th>Time Out</th>
        </tr>
        <tr>
            <td>Monday<form:input type="hidden" path="dailyReportsList[0].day" value="Monday"/></td>
            <td><form:input type="time" path="dailyReportsList[0].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[0].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
        <tr>
            <td>Tuesday<form:input type="hidden" path="dailyReportsList[1].day" value="Tuesday"/></td>
            <td><form:input type="time" path="dailyReportsList[1].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[1].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
        <tr>
            <td>Wednesday<form:input type="hidden" path="dailyReportsList[2].day" value="Wednesday"/></td>
            <td><form:input type="time" path="dailyReportsList[2].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[2].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
        <tr>
            <td>Thursday<form:input type="hidden" path="dailyReportsList[3].day" value="Thursday"/></td>
            <td><form:input type="time" path="dailyReportsList[3].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[3].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
        <tr>
            <td>Friday<form:input type="hidden" path="dailyReportsList[4].day" value="Friday"/></td>
            <td><form:input type="time" path="dailyReportsList[4].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[4].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
        <tr>
            <td>Saturday<form:input type="hidden" path="dailyReportsList[5].day" value="Saturday"/></td>
            <td><form:input type="time" path="dailyReportsList[5].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[5].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
        <tr>
            <td>Sunday<form:input type="hidden" path="dailyReportsList[6].day" value="Sunday"/></td>
            <td><form:input type="time" path="dailyReportsList[6].startTime" step="1800" size="10" pattern="[0-9:]*"/></td>
            <td><form:input type="time" path="dailyReportsList[6].finishTime" step="1800" size="10" pattern="[0-9:]*"/></td>
        </tr>
    </table>
    <br> <input type="submit" value="Submit Timesheet"/>
</form:form>
</body>
</html>