package controller;

import dao.UsuarioDao;
import dao.UsuarioDaoImplement;
import model.Usuario;

import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final UsuarioDao usuarioDao = new UsuarioDaoImplement();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identificador = request.getParameter("identificador");
        String password = request.getParameter("password");

        // Lógica de validación de inicio de sesión con Hibernate
        Usuario usuario = usuarioDao.getUsuarioPorCredenciales(identificador, password);

        // Es necesario el dispatcher para acceder a la carpeta jsp con los archivos .jsp
        RequestDispatcher dispatcher;

        if (usuario != null) {
            // Usuario válido, redirige a la página principal
            // Almacena el identificador en la sesión
            HttpSession sesion = request.getSession();
            sesion.setAttribute("identificador", identificador);
            // Almacena la contraseña en la sesión
            sesion.setAttribute("password", password);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/lobby.jsp");
        } else {
            // Usuario no válido, redirige a la página de error
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
        }

        dispatcher.forward(request, response);

    }
}
