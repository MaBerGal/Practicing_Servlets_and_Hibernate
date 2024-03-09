<%@page import="java.util.Objects"%>
<%@page import="model.Compra"%>
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
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles de Compra</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css" />
    <script src="js/scripts.js"></script> 
</head>
<body>

<%
    // Obtener el id de la compra desde los parámetros de la solicitud
    int compraId = Integer.parseInt(request.getParameter("id"));

    // Obtener la compra usando tu CompraDao
    CompraDao compraDao = new CompraDaoImplement();
    Compra compra = compraDao.getCompraPorId(compraId);

    // Verificar si la compra existe
    if (compra != null) {
%>

<!-- Mostrar detalles de la compra -->
<section>
    <h2>Compra <%= compra.getId()%></h2>
    <!-- Contenedor div para información de texto -->
    <div>
        <p><b>ID de Usuario:</b> <%= compra.getIdUsuario()%></p>
        <p><b>Descripción:</b> <%= compra.getDescripcion()%></p>
        <p><b>Precio:</b> <%= compra.getPrecio()%></p>
        <p><b>Fecha:</b> <%= compra.getFechaFormateada()%></p>
    </div>

    <img src="images/<%= compra.getImagen()%>" alt="Imagen de la Compra"/>
</section>

<!-- Formulario para modificar la compra -->
<section>
    <h2>Modificar Compra</h2>
    <form action="ModificarCompraServlet" method="post">
        <input type="hidden" name="compraId" value="<%= compra.getId()%>">
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" value="<%= compra.getDescripcion()%>" maxlength="20" required placeholder="Ingrese la Descripción" oninput="actualizarContador(this, 20)">
        <p id="contador-descripcion"></p>
        <br>
        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" value="<%= compra.getPrecio()%>" min="0" step="0.01" required placeholder="Ingrese el Precio" onkeypress="return esTeclaNumerica(event)" oninput="actualizarContador(this, 10)">
        <p id="contador-precio"></p>
        <br>
        <label for="fecha">Fecha:</label>
        <input type="date" id="fecha" name="fecha" value="<%=compra.getFechaFormateada()%>" required>
        <label for="imagen">Imagen:</label>
        <input type="file" id="imagen" name="imagen" accept="image/*">
        
        <!-- Checkbox para reemplazar la imagen original o no -->
        <label id="labelCheckbox" for="reemplazarImagen">¿Reemplazar imagen original?</label>
        <input type="checkbox" id="reemplazarImagen" name="reemplazarImagen" value="true">

        <input type="submit" value="Modificar Compra">
           
    </form>
</section>

<%
    } else {
%>

<p>Compra no encontrada</p>

<%
    }
%>

<!-- Botón para volver a la página anterior -->
<form action="RedireccionarServlet" method="get">
    <input type="hidden" name="destino" value="verCompras">
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
