<%@ page import="Data.cupcakes.Bottom" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 2/27/18
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Products page</h2>

    <h4>Buttoms flavors </h4>
    <%
        List bList = (List) session.getAttribute("listButtom");
        for(int i = 0; i < bList.size(); i++) {
        	out.println(bList.get(i) + "<br />");
        }
    %>
    <h4>Topping flavors</h4>

    <%
        List tList = (List) session.getAttribute("listTopping");
        for(int i = 0; i < tList.size(); i++) {
        	out.println(tList.get(i) + "<br />");
        }
    %>
    <br />

    <form id="formCreateCupcake" action="ServletProducts" method="post">
        <label id="labelTopping" for="topping">topping</label>
        <input type="text" id="topping" name="topping" />
        <label id="labelBottom" for="bottom">bottom</label>
        <input type="text" id="bottom" name="bottom" />
        <label id="labelQuantity" for="quantity">Quantity</label>
        <input type="number" id="quantity" name="quantity" />
        <input type="hidden" name="originPost" value="createCupcake" />
        <input type="submit" value="CREATE CUPCAKE" />
    </form>
</body>
</html>
