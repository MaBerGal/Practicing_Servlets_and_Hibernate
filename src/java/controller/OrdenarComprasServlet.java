    package controller;

    import dao.CompraDao;
    import dao.CompraDaoImplement;
    import java.io.IOException;
    import java.util.ArrayList;
    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import model.Compra;

    @WebServlet("/OrdenarComprasServlet")
    public class OrdenarComprasServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            // Obtener el criterio de orden seleccionado
            String criterioOrden = request.getParameter("orden");

            // Obtener el identificador del usuario de la sesión
            String identificador = (String) request.getSession().getAttribute("identificador");

            // Lógica para obtener la lista de compras ordenada según el criterio
            CompraDao compraDao = new CompraDaoImplement();
            ArrayList<Compra> comprasOrdenadas = compraDao.getComprasOrdenadas(identificador, criterioOrden);

            // Almacenar la lista de compras ordenada en el objeto de solicitud
            request.setAttribute("compras", comprasOrdenadas);

            // Redirigir la solicitud al JSP que muestra las compras
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/verCompras.jsp");
            dispatcher.forward(request, response);
        }
    }
