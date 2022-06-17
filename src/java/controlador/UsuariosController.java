package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import modelo.usuarios;
import modelo.usuariosDAO;

/**
 *
 * @author pato_
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/UsuariosController"})
public class UsuariosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            usuariosDAO UsuariosDAO = new usuariosDAO();
            String accion;
            RequestDispatcher dispatcher = null;

            accion = request.getParameter("accion");

            if (accion == null || accion.isEmpty()) {
                dispatcher = request.getRequestDispatcher("index.html");

            } else if ("lista".equals(accion)) {
                dispatcher = request.getRequestDispatcher("listaUsuarios.jsp");
                List<usuarios> listausuarios = UsuariosDAO.listaUsuarios();
                request.setAttribute("lista", listausuarios);

            } else if ("logear".equals(accion)) {
                dispatcher = request.getRequestDispatcher("login.jsp");
            } else if ("nuevo".equals(accion)) {
                dispatcher = request.getRequestDispatcher("registrarUsuario.jsp");
            } else if ("chat".equals(accion)) {
                
                System.out.println(request.getParameter("matricula").toString());
                dispatcher = request.getRequestDispatcher("prueba.jsp");
            } 
                dispatcher.forward(request, response);
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
