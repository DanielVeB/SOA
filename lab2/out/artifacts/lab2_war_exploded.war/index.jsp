<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 04.03.2020
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Getting started</title>
  </head>
  <body>
    <h1>Hello world</h1>
  <%
    Date date = new Date();
    out.println("Date is " + date.toString());
  %>

  </body>
</html>
