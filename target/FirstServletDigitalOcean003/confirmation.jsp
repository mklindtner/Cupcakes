<%@ page import="Data.ShoppingCart.ShoppingCart" %>
<%@ page import="Data.ShoppingCart.LineItems" %>
<%@ page import="Data.Users.User" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 2/28/18
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order page</title>
</head>
<body>
    Your Order was successful!
    <br />
    <%
        ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingCart");
        User u = (User) session.getAttribute("user");
        int totalPrice = 0;
        for(int i = 0; i < sc.getList().size(); i++) {
            LineItems li = sc.getItem(i);
        	totalPrice += li.getQuantity() * li.getCupcake().getTotalPrice();
        }
        out.print("Your total price is: " + totalPrice);
        out.print("Your remaining balance is: " + u.getBalance());
    %>
</body>
</html>
