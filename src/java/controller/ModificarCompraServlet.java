package controller;

import dao.CompraDao;
import dao.CompraDaoImplement;
import model.Compra;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ModificarCompraServlet")
public class ModificarCompraServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtén los parámetros del formulario de modificación de compra
        int compraId = Integer.parseInt(request.getParameter("compraId"));
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String fechaString = request.getParameter("fecha");
        boolean reemplazarImagen = Boolean.parseBoolean(request.getParameter("reemplazarImagen")); // Nuevo parámetro

        // Si se selecciona el checkbox de reemplazar imagen, obtenemos la nueva imagen
        String nuevaImagen = "";
        if (reemplazarImagen) {
            nuevaImagen = request.getParameter("imagen");
        } else {
            // Si no se selecciona el checkbox, conservamos la imagen original
            CompraDao compraDao = new CompraDaoImplement();
            Compra compraOriginal = compraDao.getCompraPorId(compraId);
            if (compraOriginal != null) {
                nuevaImagen = compraOriginal.getImagen();
            }
        }

        // Convierte la cadena de fecha a un objeto Date
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Obtén la compra desde la base de datos
        CompraDao compraDao = new CompraDaoImplement();
        Compra compra = compraDao.getCompraPorId(compraId);

        // Verifica si la compra existe
        if (compra != null) {
            // Actualiza los datos de la compra
            compra.setDescripcion(descripcion);
            compra.setPrecio(precio);
            compra.setFecha(fecha);
            compra.setImagen(nuevaImagen); // Utiliza la nueva imagen

            // Guarda los cambios en la base de datos
            compraDao.editarCompra(compra);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/verDetalles.jsp?id=" + compraId);
        dispatcher.forward(request, response);
    }
}
