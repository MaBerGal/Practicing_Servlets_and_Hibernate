package controller;

import dao.CompraDao;
import dao.CompraDaoImplement;
import model.Compra;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BorrarCompraServlet")
public class BorrarCompraServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtén el ID de la compra a borrar desde el formulario
        int compraId = Integer.parseInt(request.getParameter("compraId"));

        // Obtén la compra desde la base de datos
        CompraDao compraDao = new CompraDaoImplement();
        Compra compra = compraDao.getCompraPorId(compraId);

        if (compra != null) {
            // Borra la compra de la base de datos
            compraDao.borrarCompra(compra);
        }

        // Redirige a la página de ver compras
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/verCompras.jsp");
        dispatcher.forward(request, response);
    }
}
