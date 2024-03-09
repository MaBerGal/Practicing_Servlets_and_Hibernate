/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/RedireccionarServlet")
public class RedireccionarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String destino = request.getParameter("destino");
         
        // Verificar si hay una sesión activa
        HttpSession session = request.getSession(false);
         if (session == null || session.getAttribute("identificador") == null) {
            // No hay sesión
            if ("index".equals(destino)) {
                // El destino es index.jsp, redirigir a index.jsp
                RequestDispatcher indexDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
                indexDispatcher.forward(request, response);
            } else {
                // Redirigir a la página de error para otros destinos
                RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/noAutentificado.jsp");
                errorDispatcher.forward(request, response);
            }
            return;  // Importante detener la ejecución del servlet después de redirigir
        }
        
       
        if ("verCompras".equals(destino)) {

            // Redirigir a verCompras.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/verCompras.jsp");
            dispatcher.forward(request, response);
        } else if ("verTablas".equals(destino)) {

            // Redirigir a verTablas.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/verTablas.jsp");
            dispatcher.forward(request, response);
        } else if ("lobby".equals(destino)) {

            // Redirigir a lobby.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/lobby.jsp");
            dispatcher.forward(request, response);
        } else if ("index".equals(destino)) {

            // Redirigir a index.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
            dispatcher.forward(request, response);
        } else if ("verDetalles".equals(destino)) {

            // Redirigir a verDetalles.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/verDetalles.jsp");
            dispatcher.forward(request, response);
        } else {
            // Manejar otros destinos si es necesario
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}


