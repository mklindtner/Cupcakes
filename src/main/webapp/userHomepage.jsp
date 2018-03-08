<%@ page import="Data.Users.User" %>
<%@ page import="Data.cupcakes.Bottom" %>
<%@ page import="java.util.List" %>
<%@ page import="Data.ShoppingCart.ShoppingCart" %>
<%@ page import="Data.ShoppingCart.LineItems" %>
<%@ page import="Data.cupcakes.Cupcake" %>
<%@ page import="Data.cupcakes.Topping" %>
<%@ page import="javax.sound.sampled.Line" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 2/26/18
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
</head>
<body>
    <h1>User</h1>
    <%
        User user = (User) session.getAttribute("user");
        if (user != null)
        {
            out.print("Login worked");
        }
        else
        {
            out.print("Login failed");
            user = new User("ano", "");
        }
    %>

    <p>
        Username: <%= user.getUsername() %><br>
        Balance: <%= user.getBalance() %><br>
    </p>

    <h3>Items in shoppingcart</h3>
    <%
        ShoppingCart sc = (ShoppingCart) session.getAttribute("shoppingCart");
        int totalPrice = 0;
        //for testing until next if
        if(user.getUsername().equalsIgnoreCase("usercake1") && sc == null) {
                sc = new ShoppingCart();
                Cupcake cp = new Cupcake(new Topping(100, 10, "Blueberry"), new Bottom(1, 10, "Nutmeg"));
                LineItems li = new LineItems(100, cp);
                sc.add(li);
                request.getSession().setAttribute("shoppingCart", sc);
        }
        if(sc != null) {
        	totalPrice = 0;
        	for(int i = 0; i < sc.getList().size(); i++) {
        		out.print(sc.getItem(i) + "<br />");
        		totalPrice += sc.getItem(i).getCupcake().getTotalPrice() * sc.getItem(i).getQuantity();
        	}
        	if(user.getBalance() < totalPrice) {
        		out.println("you can currently not afford this shoppingCart");
            } else
        	    out.println("Your total price is: " + totalPrice);
        }



    %>
    <form action="ServletProducts" method="post">
        <input type="hidden" name="originPost" value="clearCart" />
        <input type="submit" value="Delete Items in shoppingCart" />
    </form>


    <h4>Cupcakes</h4>
    <form action="ServletProducts" method="get">
        <input type="hidden" name="originGet" value="ButTop" />
        <input type="submit" value="Buy Cupcake" />
    </form>

    <%
        if(user.isAdmin()) { %>
        <form action="Servlet001" method="get">
            <input type="hidden" name="originGet" value="Admin" />
            <input type="submit" value="AdminPage" />
        </form>
    <% } %>

    <form action="ServletProducts" method="post">
        <input type="hidden" name="originPost" value="Confirmation" />
        <input type="submit" value="Purchase" />
    </form>

    <form action="ServletProducts" method="post">
        <input type="hidden" name="originPost" value="invoices" />
        <input type="submit" value="see Invoices">
    </form>


</body>
</html>
