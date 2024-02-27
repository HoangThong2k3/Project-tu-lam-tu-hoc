<%--
  Created by IntelliJ IDEA.
  User: ticha
  Date: 1/28/2024
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new student</title>
</head>
<body>
<h1>Add new student</h1>
<form method="post">
    <label for="Name">Name:</label>
    <input id="Name" name="Name" type="text" required>
    <label for="Email">Email:</label>
    <input id="Email" name="Email" type="email" required>
    <label for="Phone">Phone:</label>
    <input id="Phone" name="Phone" type="text" required>
    <button type="submit">Add Student</button>
</form>
</body>
</html>
