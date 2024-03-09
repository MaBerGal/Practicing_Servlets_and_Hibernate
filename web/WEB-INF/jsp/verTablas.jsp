<%@page import="java.util.Objects"%>
<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    boolean usuarioAutenticado = session != null && Objects.nonNull(session.getAttribute("identificador"));

    // Si el usuario no est� autenticado, redirige a noautentificado.jsp
    if (!usuarioAutenticado) {
        response.sendRedirect("noautentificado.jsp");
    }
%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Compra"%>
<%@page import="dao.CompraDao"%>
<%@page import="dao.CompraDaoImplement"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ver Tablas</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css" />
</head>
<body>

    <!-- Header -->
    <header>
        <h1>Tabla de Compras</h1>
    </header>

    <div style="overflow-x: auto;">
        <%
            // Obtener la lista de compras usando tu CompraDao
            CompraDao compraDao = new CompraDaoImplement();
            String identificador = (String) session.getAttribute("identificador");
            ArrayList<Compra> compras = compraDao.getComprasPorUsuario(identificador);

            // Verificar si hay compras para mostrar
            if (compras != null && !compras.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre de Usuario</th>
                    <th>Descripci�n</th>
                    <th>Precio</th>
                    <th>Fecha</th>
                    <!-- Agrega m�s encabezados seg�n sea necesario -->
                </tr>
            </thead>
            <tbody>
                <%
                    // Iterar sobre las compras y mostrar la informaci�n en la tabla
                    for (Compra compra : compras) {
                %>
                <tr>
                    <td><%= compra.getId()%></td>
                    <td><%= compra.getIdUsuario()%></td>
                    <td><%= compra.getDescripcion()%></td>
                    <td><%= compra.getPrecio()%></td>
                    <td><%= compra.getFecha()%></td>
                    <!-- Agrega m�s celdas seg�n sea necesario -->
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <h2>No hay compras para mostrar.</h2>
        <%
            }
        %>
    </div>

    <!-- Bot�n para volver a la p�gina anterior -->
    <form action="RedireccionarServlet" method="get">
        <input type="hidden" name="destino" value="lobby">
        <input class="botonRedireccionar" type="submit" value="Volver a la p�gina anterior">
    </form>

    <!-- Bot�n para cerrar sesi�n -->
    <aside>
         <br>
        
        <form action="CerrarSesionServlet" method="post">
            <input class="botonCerrarSesion" type="submit" value="Cerrar Sesi�n">
        </form>
    </aside>

    <!-- Footer -->
    <footer>
        <p class="pie">MaBerGal - � 2024</p>
    </footer>

</body>
</html>
