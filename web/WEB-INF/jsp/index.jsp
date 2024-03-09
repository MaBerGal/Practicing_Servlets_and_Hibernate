<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    boolean usuarioAutenticado = session != null && Objects.nonNull(session.getAttribute("identificador"));
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css" />
     <script src="js/scripts.js"></script> 
</head>
<body class="bodyFondo">
    
    <header><h1>Bienvenido a la Práctica 10 de PSP</h1></header>

<% if (usuarioAutenticado) { %>

   <!-- Botón para volver a la página anterior -->
    <form action="RedireccionarServlet" method="get">
        <input type="hidden" name="destino" value="lobby">
        <input class="botonRedireccionar" type="submit" value="Volver al lobby">
    </form>
   <br>
    <!-- Botón para cerrar sesión -->
    <aside>
        <form action="CerrarSesionServlet" method="post">
            <input class="botonCerrarSesion" type="submit" value="Cerrar Sesión">
        </form>
    </aside>
    <% } else { %>
    <!-- Pestañas -->
    <div class="tab-container">
        <div class="tab active" id="loginTab" onclick="mostrarContenido('loginTab')">Iniciar Sesión</div>
        <div class="tab" id="registerTab" onclick="mostrarContenido('registerTab')">Registrarse</div>
    </div>

    <!-- Contenido de las pestañas -->
    <div id="loginTab-content" class="tab-content active">
        <!-- Formulario de Iniciar Sesión -->
        <form action="LoginServlet" method="post" onsubmit="return validarIdentificador();">
            <label for="identificador">Usuario:</label>
            <input type="text" id="identificador" name="identificador" maxlength="20" oninput="validarIdentificador(); actualizarContador(this, 20)" required placeholder="Ingrese su usuario">
            <span id="contador-identificador"></span>
            <p class="errorValidacion"></p>
            <br>
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="password" maxlength="20" oninput="actualizarContador(this, 20)" required placeholder="Ingrese su contraseña">
            <span id="contador-contrasena"></span>
            <br>
            <br>
            <input type="submit" value="Iniciar Sesión">
        </form>
    </div>

    <div id="registerTab-content" class="tab-content">
        <!-- Formulario de Registrarse -->
        <form action="RegistroServlet" method="post" onsubmit="return validarRegistro();">
            <label for="nuevo-identificador">Nuevo Usuario:</label>
            <input type="text" id="nuevo-identificador" name="nuevo-identificador" maxlength="20" oninput="actualizarContador(this, 20)" required placeholder="Ingrese su nuevo usuario">
            <span id="contador-nuevo-identificador"></span>
            <p class="errorValidacion"></p>
            <br>
            <label for="nueva-contrasena">Contraseña:</label>
            <input type="password" id="nueva-contrasena" name="nueva-contrasena" maxlength="20" oninput="actualizarContador(this, 20)" required placeholder="Ingrese su contraseña">
            <span id="contador-nueva-contrasena"></span>
            <br>
            <br>
            <input type="submit" value="Registrarse">
        </form>
    </div>
    <% } %>

<footer>
    <p>MaBerGal - © 2024</p>
</footer>

</body>
</html>
