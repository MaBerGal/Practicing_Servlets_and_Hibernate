<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css" />
</head>
<body>
    <h1>No est�s autorizado para visualizar este contenido.</h1>
    
    <!-- Bot�n para volver a la p�gina anterior -->
    <form action="RedireccionarServlet" method="get">
        <input type="hidden" name="destino" value="index">
        <input class="botonRedireccionar" type="submit" value="Volver a la p�gina de inicio">
    </form>
</body>
</html>
