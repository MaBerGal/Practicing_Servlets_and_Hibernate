<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css" />
</head>
<body>
    <h1>No estás autorizado para visualizar este contenido.</h1>
    
    <!-- Botón para volver a la página anterior -->
    <form action="RedireccionarServlet" method="get">
        <input type="hidden" name="destino" value="index">
        <input class="botonRedireccionar" type="submit" value="Volver a la página de inicio">
    </form>
</body>
</html>
