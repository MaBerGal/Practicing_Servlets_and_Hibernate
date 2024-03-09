package controller;

import dao.CompraDao;
import dao.CompraDaoImplement;
import model.Compra;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/InsertarCompraServlet")
public class InsertarCompraServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener datos
        HttpSession session = request.getSession();
        String idUsuario = (String) session.getAttribute("identificador");
        String descripcion = request.getParameter("descripcion");
        
        // Usar BigDecimal para manejar precisión decimal
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        
        String fechaString = request.getParameter("fecha");
        String imagen = request.getParameter("imagen");

        // Convierte la cadena de fecha a un objeto Date
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Obtener el siguiente código para la compra
        CompraDao compraDao = new CompraDaoImplement();
        int nextCode = compraDao.getSiguienteID();

        // Crea un nuevo objeto Compra con el siguiente código
        Compra nuevaCompra = new Compra();
        nuevaCompra.setId(nextCode);
        nuevaCompra.setIdUsuario(idUsuario);
        nuevaCompra.setDescripcion(descripcion);
        nuevaCompra.setPrecio(precio.doubleValue()); // Convierte BigDecimal a double
        nuevaCompra.setFecha(fecha);
        nuevaCompra.setImagen(imagen);

        // Inserta la nueva compra en la base de datos
        compraDao.insertarCompra(nuevaCompra);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/verCompras.jsp");
        dispatcher.forward(request, response);
    }
}
