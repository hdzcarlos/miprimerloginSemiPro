<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesion</title>
    </head>
    <body>
        <h1>Registro</h1>
        <p><a href="register.jsp">Registrarse</a></p>
        <p style="color: #ff0000">${sessionScope['error']}</p>
        <form action="Login" method="post">
            <p> Email: <input type="text" name="email"></p>
            <p> Contraseña: <input type="password" name="password"></p>
            <p><input type="submit" value="Entrar"></p>
        </form>
        <h1>Inicia</h1>
                <p><a href="login.jsp">Iniciar Sesion</a></p>

                <p style="color: #ff0000">${sessionScope['error']}</p>
                <form method="post"     action="RegistrarUsuarios">
                    <p>Nombre: <input type="text" name="name"></p>
                    <p>Email: <input type="text" name="email"></p>
                    <p>Contraseña: <input type="password" name="password1"></p>
                    <p>Confirma contraseña <input type="password" name="password2"></p>
                    <p><input type="submit" value="Entrar"></p>
                </form>
    </body>
</html>