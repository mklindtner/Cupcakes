<%@ page import="Data.cupcakes.ProductDAO" %>
<%@ page import="Data.cupcakes.Topping" %>
<%@ page import="java.util.List" %>
<%@ page import="Data.cupcakes.Cupcake" %>
<%@ page import="Data.cupcakes.Invoice" %>
<%@ page import="Data.Users.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 3/7/18
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invoice Details</title>
</head>
<body>
    <h3>A test </h3>

    <%
        List cupcakeList = (List) request.getSession().getAttribute("cupcakeList");
        List invoiceList = (List) request.getSession().getAttribute("invoiceList");
        int indexInvoice =  Integer.parseInt(request.getParameter("id"));
        Invoice invoice = null;

        for(int i = 0; i < invoiceList.size(); i++) {
            invoice = (Invoice) invoiceList.get(i);
            if(invoice.getId() == indexInvoice) {
            	break;
                //break; instead remove null?
            }
        }
        for(int i = 0; i < cupcakeList.size(); i++) {
            Cupcake c = (Cupcake) cupcakeList.get(i);
            if(c.getBotID() == invoice.getBotID() && c.getTopID() == invoice.getTopId())
                out.print("cupcake: " + c.toString() + ", paid: " + c.getTotalPrice() + "<br />");
        }
    %>
</body>
</html>
