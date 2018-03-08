<%@ page import="Data.cupcakes.Invoice" %>
<%@ page import="java.util.List" %>
<%@ page import="Data.Users.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Data.cupcakes.Cupcake" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 3/7/18
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User invoice</title>
</head>
<body>
    welcome to your invoice
    <%
        User user = (User) request.getSession().getAttribute("user");
        List cupcakes = (List) request.getSession().getAttribute("cupcakeList");
        out.print("username: " + user.getUsername() + "<br />");
        Cupcake c = null;

        if(user.isAdmin()) {
            List invoices = (List) request.getSession().getAttribute("invoiceList");
            for(int i = 0; i < invoices.size(); i++) {
            	Invoice invoice = (Invoice) invoices.get(i);
            	c = (Cupcake) cupcakes.get(i);
            	out.println("<a href='invoiceDetails.jsp?id=" + invoice.getId() + "'>" + invoice.toString() + "</a>, " + c.toString() + ", user: " + invoice.getUserId() + "<br />");
            }
        } else {
            List invoiceList = (List) request.getSession().getAttribute("invoiceList");
            for(int i = 0; i < invoiceList.size(); i++) {
                Invoice invoice = (Invoice) invoiceList.get(i);
                c = (Cupcake) cupcakes.get(i);
                out.println("<a href='invoiceDetails.jsp?id=" + invoice.getId() + "'>" + invoice.toString() + "</a>, " + c.toString() + ", user: " + invoice.getUserId() + "<br />");
            }
        }
    %>

    <form action="Servlet001" method="get">
        <input type="hidden" name="originGet" value="redirect"/>
        <input type="submit" value="userPage" />
    </form>

</body>
</html>
