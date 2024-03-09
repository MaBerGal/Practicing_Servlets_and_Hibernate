package controller;

import dao.CompraDao;
import dao.CompraDaoImplement;
import dao.UsuarioDao;
import dao.UsuarioDaoImplement;
import model.Compra;
import model.Usuario;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LobbyServlet")
public class LobbyServlet extends HttpServlet {
    private final UsuarioDao usuarioDao = new UsuarioDaoImplement();
    private final CompraDao compraDao = new CompraDaoImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el identificador del usuario desde la sesión
        HttpSession session = request.getSession();
        String identificador = (String) session.getAttribute("identificador");
        String password = (String) session.getAttribute("password");

        // Validar si el usuario está autenticado
        if (identificador != null && password != null) {
            // Obtener el usuario por identificador
            Usuario usuario = usuarioDao.getUsuarioPorCredenciales(identificador, password);

            // Validar si el usuario existe
            if (usuario != null) {
                // Obtener todas las compras relacionadas con el usuario
                ArrayList<Compra> compras = compraDao.getComprasPorUsuario(identificador);

                // Calcular el total de dinero en compras
                BigDecimal totalCompras = BigDecimal.ZERO;
                for (Compra compra : compras) {
                    // Convertir el precio (double) a BigDecimal antes de sumarlo
                    BigDecimal precioComoBigDecimal = BigDecimal.valueOf(compra.getPrecio());
                    totalCompras = totalCompras.add(precioComoBigDecimal);
                }

                // Colocar el total en el alcance de la solicitud para que pueda ser utilizado en el JSP
                request.setAttribute("totalCompras", totalCompras);

                // Redirigir a la página JSP
                request.getRequestDispatcher("/WEB-INF/jsp/lobby.jsp").forward(request, response);
            } else {
                // Usuario no válido, redirige a la página de error
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            }
        } else {
            // Usuario no autenticado, redirige a la página de inicio de sesión
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}


