<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesion</title>
    </head>
    <body>
        <h1>Iniciar sesion</h1>
        <p><a href="register.jsp">Registrarse</a></p>
        <p style="color: #ff0000">${sessionScope['error']}</p>
        <form action="Login" method="post">
            <p> Email: <input type="text" name="email"></p>
            <p> Contraseña: <input type="password" name="password"></p>
            <p><input type="submit" value="Entrar"></p>
        </form>
    </body>
</html>