package pe.edu.upc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.edu.upc.bean.Usuario;
import pe.edu.upc.util.WebUtil;

@WebServlet(name = "SesionController", urlPatterns = {"/SesionController"})
public class SesionController extends HttpServlet {

    protected void iniciar(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String codigo = request.getParameter("txtUsuario").trim();
        String password = request.getParameter("txtPassword");

        if (WebUtil.validarCredenciales(codigo, password) != null) {
            response.sendRedirect("iniciarSesion.jsp?messageRequired=" + WebUtil.validarCredenciales(codigo, password));
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setUsuario(codigo);
        usuario.setFechaInicioSesion(new java.util.Date());

        HttpSession session = request.getSession(true);
        session.setAttribute("sesionUsuario", usuario);

        response.sendRedirect("listarClientes.jsp?messageSuccess=Bienvenido Administrador.");
    }

    protected void cerrar(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        session.removeAttribute("sesionUsuario");
        session.invalidate();
        response.sendRedirect("iniciarSesion.jsp");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("txtAction");
        try {
            if (accion.equalsIgnoreCase("iniciar")) {
                iniciar(request, response);
            } else if (accion.equalsIgnoreCase("cerrar")) {
                cerrar(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("iniciarSesion.jsp?messageError="
                    + "Error en el sistema, por favor, vuelva a intentarlo.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
