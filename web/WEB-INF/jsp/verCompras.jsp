<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Compra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CompraDao"%>
<%@page import="dao.CompraDaoImplement"%>
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
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Compras</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css" />
    <script src="js/scripts.js"></script> 
</head>
<body>

    <!-- Header -->
    <header>
        <h1>Lista de Compras</h1>
    </header>
    
    <form action="OrdenarComprasServlet" method="get">
        <!-- Combo box para ordenar las compras -->
        <label for="orden">Ordenar por:</label>
        <select id="orden" name="orden">
            <option value="fechaAsc">Fecha Ascendente</option>
            <option value="fechaDesc">Fecha Descendente</option>
            <option value="masCaro">Más Caro</option>
            <option value="masBarato">Más Barato</option>
        </select>

        <!-- Botón para aplicar el orden -->
        <input type="submit" value="Aplicar Orden">
    </form>


    <%-- Lógica para obtener y mostrar las compras --%>
    <%
        // Obtener la lista de compras usando tu CompraDao
        CompraDao compraDao = new CompraDaoImplement();
        // Obtener el identificador del usuario de la sesión
        String identificador = (String) session.getAttribute("identificador");
        // Obtener la lista de compras ordenadas según el criterio seleccionado
        ArrayList<Compra> comprasOrdenadas = compraDao.getComprasOrdenadas(identificador, request.getParameter("orden"));

        if (comprasOrdenadas != null) {

            // Iterar sobre las compras ordenadas y mostrar la información
            for (Compra compra : comprasOrdenadas) {
    %>
    <section>
        <h2>Compra ID: <%= compra.getId()%></h2>
        <!-- Artículo para mostrar información de la compra -->
        <article class="elemento">
            <!-- Contenedor div para información de texto -->
            <div>
                <p><b>Descripción:</b> <%= compra.getDescripcion()%></p>
                <p><b>Precio:</b> <%= compra.getPrecio()%></p>
                <p><b>Fecha:</b> <%= compra.getFechaFormateada()%></p>
                <!-- Agrega el enlace a verDetalles.jsp aquí -->
                <a href="RedireccionarServlet?destino=verDetalles&id=<%= compra.getId()%>">Ver Detalles</a>
            </div>
            <!-- Agrega la lógica para mostrar la imagen aquí -->
            <img class="imgLista" src="images/<%= compra.getImagen()%>" alt="Imagen de la Compra"/>
        </article>
            <!-- Agrega el botón de borrar con un formulario -->
            <form action="BorrarCompraServlet" method="post">
                <input type="hidden" name="compraId" value="<%= compra.getId()%>">
                <input class="botonBorrar" type="submit" value="🗑️">
            </form>
    </section>
    <%
            }

        } else {
    %>
    <!-- Código si no hay compras para mostrar -->
    <h2>No hay compras para mostrar.</h2>
    <%
        }
    %>
    
    <!-- Formulario para crear una nueva compra -->
    <section>
        <h2>Nueva Compra</h2>
        <form action="InsertarCompraServlet" method="post">
            <label for="descripcion">Descripción:</label>
            <input type="text" id="descripcion" name="descripcion" maxlength="20" required placeholder="Ingrese la Descripción" oninput="actualizarContador(this, 20)">
            <p id="contador-descripcion"></p>
            <br>
            <label for="precio">Precio:</label>
            <input type="number" id="precio" name="precio" min="0" step="0.01" required placeholder="Ingrese el Precio" onkeypress="return esTeclaNumerica(event)" oninput="actualizarContador(this, 10)">
            <p id="contador-precio"></p>
            <br>
            <label for="fecha">Fecha:</label>
            <input type="date" id="fecha" name="fecha" required>
            <br>
            <label for="imagen">Imagen:</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">
            <br>
            <input class="botonAgregar" type="submit" value="Crear Compra">
        </form>
    </section>

    <!-- Botón para volver a la página anterior -->
    <form action="RedireccionarServlet" method="get">
        <input type="hidden" name="destino" value="lobby">
        <input class="botonRedireccionar" type="submit" value="Volver a la página anterior">
    </form>
    
    <!-- Botón para cerrar sesión -->
    <aside>
        <br>
        
        <form action="CerrarSesionServlet" method="post">
            <input class="botonCerrarSesion" type="submit" value="Cerrar Sesión">
        </form>
    </aside>

    <!-- Footer -->
    <footer>
        <p class="pie">MaBerGal - © 2024</p>
    </footer>

</body>
</html>
