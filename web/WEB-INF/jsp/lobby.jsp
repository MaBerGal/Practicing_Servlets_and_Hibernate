<%@page import="java.util.Objects"%>
<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    boolean usuarioAutenticado = session != null && Objects.nonNull(session.getAttribute("identificador"));

    // Si el usuario no está autenticado, redirige a noautentificado.jsp
    if (!usuarioAutenticado) {
        response.sendRedirect("noautentificado.jsp");
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lobby del Usuario</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css" />
</head>
<body class="bodyFondo2">

    <%-- Header --%>
    <header>
        <h1>¡Bienvenido <%= session.getAttribute("identificador")%>!</h1>
        <nav>
            
                <a href="RedireccionarServlet?destino=verCompras">Ver Compras</a>
        <br>
                <a href="RedireccionarServlet?destino=verTablas">Ver Tablas</a>
            
        </nav>
    </header>

    <%-- Lógica de cálculos (muestra el total de dinero en compras) --%>
    <%
    Object totalCompras = request.getAttribute("totalCompras");
    if (totalCompras != null) {
    %>
            <p>Total de dinero en compras: <%= totalCompras %>€</p>
    <%
        }
    %>


    <%-- Botón para recalcular el total de compras --%>
    <form action="LobbyServlet" method="get">
        <input type="submit" value="Calcular total en compras">
    </form>

    <!-- Botón para volver a la página anterior -->
    <form action="RedireccionarServlet" method="get">
        <input type="hidden" name="destino" value="index">
        <input class="botonRedireccionar" type="submit" value="Volver a la página anterior">
    </form>

    <%-- Aside con el botón de cerrar sesión --%>
    <aside>
        <form action="CerrarSesionServlet" method="post">
            <input class="botonCerrarSesion" type="submit" value="Cerrar Sesión">
        </form>
    </aside>

    <%-- Footer --%>
    <footer>
        <p class="pie">MaBerGal - © 2024</p>
    </footer>

</body>
</html>
