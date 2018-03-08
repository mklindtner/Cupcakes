<%@ page import="java.util.List" %>
<%@ page import="Data.Users.UserDAO" %>
<%@ page import="Data.Users.User" %><%--
  Created by IntelliJ IDEA.
  User: mkl
  Date: 2/28/18
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin age</title>
</head>
<body>
    <h2>Welcome to the admin page</h2>
    <%
        List users = (List) session.getAttribute("users");
        	out.println("<br /> users Table:");
        	for(int i = 0; i < users.size(); i++) {
        		out.println("<br /> " + users.get(i).toString());
            }
    %>

    <h3>Delete USER</h3>
    <form id="formDelete" action="Servlet001" method="post">
        <label id="labelUsernameDelet" for="usernameDelete">Username</label>
        <input type="text" id="usernameDelete" name="usernameDelete" />
        <input type="hidden" name="originPost" value="delete" />
        <input type="submit" value="DELETE USER" />
    </form>

    <h3>CREATE USER</h3>
    <form id="formCreate" action="Servlet001" method="post">
        <label id="labelUsername" for="username">Username</label>
        <input type="text" id="username" name="username" />
        <label id="labelPassword" for="password">Password</label>
        <input type="text" id="password" name="password" />
        <label id="labelEmail" for="email">Email</label>
        <input type="text" id="email" name="email" />
        <input type="hidden" name ="originPost" value="adminCreate" />
        <input type="submit" value="CREATE USER" />
    </form>

    <h3>Update USER</h3>
    <form id="formUpdate" action="Servlet001" method="post">
        <label id="labelUsernameUpdate" for="usernameUpdate">Username</label>
        <input type="text" id="usernameUpdate" name="usernameUpdate" />
        <input type="hidden" name="originPost" value="update" />
        <input type="submit" value="UPDATE USER" />
    </form>

    <h3>Order List</h3>
    <form id="formRedirect" action="ServletProducts" method="post">
        <input type="hidden" name="originPost" value="invoices" />
        <input type="submit" value="Display Orders" />
    </form>

</body>
</html>
