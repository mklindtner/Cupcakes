<%@ page import="java.util.List" %>
<%@ page import="Data.cupcakes.Invoice" %>
<%@ page import="Data.Users.User" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 3/7/18
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Specific invoice</title>
</head>
<body>
    <%
        List users = (List) request.getSession().getAttribute("users");
        List invoiceList = (List) request.getSession().getAttribute("invoiceList");
        int indexChoice = Integer.parseInt(request.getParameter("id"));
        int minList = Math.min(users.size(), invoiceList.size());
        for(int i = 0; i < minList; i++) {
        	Invoice invoice = (Invoice) invoiceList.get(i);
        	User    user    = (User) users.get(i);
        	if(invoice.getId() == indexChoice) {
        		out.print(invoice.toString());
            }

        }
    %>
</body>
</html>
