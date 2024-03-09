package controller;

import dao.UsuarioDao;
import dao.UsuarioDaoImplement;
import model.Usuario;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtén los parámetros del formulario de registro
        String nuevoIdentificador = request.getParameter("nuevo-identificador");
        String nuevaContrasena = request.getParameter("nueva-contrasena");

        // Verifica que los parámetros no sean nulos o vacíos
        if (Objects.nonNull(nuevoIdentificador) && Objects.nonNull(nuevaContrasena) &&
                !nuevoIdentificador.isEmpty() && !nuevaContrasena.isEmpty()) {

            // Verifica si ya existe un usuario con el mismo identificador
            UsuarioDao usuarioDao = new UsuarioDaoImplement();
            Usuario usuarioExistente = usuarioDao.getUsuarioPorCredenciales(nuevoIdentificador, nuevaContrasena);

            if (usuarioExistente == null) {
                // No existe un usuario con el mismo identificador, procede con el registro

                // Crea un nuevo usuario
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setId(nuevoIdentificador);
                nuevoUsuario.setPassword(nuevaContrasena);

                // Inserta el nuevo usuario en la base de datos
                usuarioDao.insertarUsuario(nuevoUsuario);

                // Utiliza RequestDispatcher para redirigir a la página de registro correcto
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registroCorrecto.jsp");
                dispatcher.forward(request, response);
            } else {
                // Ya existe un usuario con el mismo identificador, redirige a la página de registro incorrecto
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registroIncorrecto.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            // Maneja el caso en el que los parámetros son nulos o vacíos
            // Utiliza RequestDispatcher para redirigir a la página de error
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
