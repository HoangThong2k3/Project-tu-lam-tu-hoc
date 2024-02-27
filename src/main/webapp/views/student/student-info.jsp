<%@ page import="models.student.Student" %><%--
  Created by IntelliJ IDEA.
  User: ticha
  Date: 1/21/2024
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Student student = (Student) request.getAttribute("student");
%>
<html>
<head>
    <title>Student info</title>
</head>
<body>
    <%
        if (student != null) {
    %>
        <h1>Student info</h1>
        <h3>Student name: <%=student.getName()%></h3>
        <h3>Student email: <%=student.getEmail()%></h3>
        <h3>Student phone: <%=student.getPhone()%></h3>
    <%
        } else {
    %>
    <h1>Student not found</h1>
      <%
          }
      %>

</body>
</html>
