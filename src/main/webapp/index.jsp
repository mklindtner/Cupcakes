<html>
<body>
    <h3>Login</h3>

    <form action="Servlet001" method="post">
        <input type="text" name="username" value="" placeholder="Username" />
        <input type="password" name="password" value="" placeholder="Password" />
        <input type="hidden" name="originPost" value="login" />
        <input type="submit" value="Log in" />
    </form>


    <h3>CREATE USER</h3>

    <form id="formCreate" action="Servlet001" method="post">
        <label id="labelUsername" for="username">Username</label>
        <input type="text" id="username" name="username" />
        <label id="labelPassword" for="password">Password</label>
        <input type="text" id="password" name="password" />
        <label id="labelEmail" for="email">Email</label>
        <input type="text" id="email" name="email" />
        <input type="hidden" name="originPost" value="create" />
        <input type="submit" value="CREATE USER" />
    </form>

</body>

</body>
</html>
