<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<head>
    <meta charset="UTF-8">
    <title>TimeSheet</title>
</head>
<body>
Week Ending: <br> <input name="userId" type="date"> <br> <br>
Employee Name: <br> <br>
<form action="addHours" method="post">
    <table>
        <tr>
            <th>Date</th>
            <th>Day</th>
            <th>Time In</th>
            <th>Time Out</th>
            <th>Total</th>
        </tr>
        <tr>
            <td></td>
            <td>Monday</td>
            <td><input name="userId" type="time" step="1800" size="10" pattern="[0-9:]*"></td>
            <td><input name="userId" type="time" step="1800" pattern="[0-9:]*"></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>Monday</td>
            <td><input name="userId" type="time" step="1800" size="10" pattern="[0-9:]*"></td>
            <td><input name="userId" type="time" step="1800" pattern="[0-9:]*"></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>Monday</td>
            <td><input name="userId" type="time" step="1800" size="10" pattern="[0-9:]*"></td>
            <td><input name="userId" type="time" step="1800" pattern="[0-9:]*"></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>Monday</td>
            <td><input name="userId" type="time" step="1800" size="10" pattern="[0-9:]*"></td>
            <td><input name="userId" type="time" step="1800" pattern="[0-9:]*"></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>Monday</td>
            <td><input name="userId" type="time" step="1800" size="10" pattern="[0-9:]*"></td>
            <td><input name="userId" type="time" step="1800" pattern="[0-9:]*"></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>Monday</td>
            <td><input name="userId" type="time" step="1800" size="10" pattern="[0-9:]*"></td>
            <td><input name="userId" type="time" step="1800" pattern="[0-9:]*"></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>Monday</td>
            <td><input name="userId" type="time" step="1800" size="10" pattern="[0-9:]*"></td>
            <td><input name="userId" type="time" step="1800" pattern="[0-9:]*"></td>
            <td></td>
        </tr>
    </table>
    <p class="tab"><b>Weekly total hours:</b></p>
    <p class="tab"><b>Rate per hour:</b></p>
    <input type="submit" value="Submit Timesheet"/>
</form>
</body>
</html>