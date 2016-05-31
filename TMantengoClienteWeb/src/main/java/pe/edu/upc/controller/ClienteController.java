package pe.edu.upc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.edu.upc.model.Cliente;
import pe.edu.upc.service.TMantengoClienteService;
import pe.edu.upc.util.WebUtil;

@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

    private TMantengoClienteService clienteService;

    protected Cliente obtenerCliente(HttpServletRequest request) throws Exception {
        String nombre = request.getParameter("txtNombre").replaceAll("\\s+", " ").trim().toUpperCase();
        String apellido = request.getParameter("txtApellido").replaceAll("\\s+", " ").trim().toUpperCase();
        String dni = request.getParameter("txtDni").trim();
        String edad = request.getParameter("txtEdad").trim();
        String sexo = request.getParameter("rdoSexo");
        String estudios = request.getParameter("cboEstudios");
        String telefono = request.getParameter("txtTelefono").trim();

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setEdad(edad);
        cliente.setSexo(sexo);
        cliente.setNivelEstudios(estudios);
        cliente.setTelefono(telefono);
        return cliente;
    }

    protected void registrar(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String mensaje = null;
        Cliente cliente = obtenerCliente(request);

        mensaje = WebUtil.validarCliente(cliente);
        if (mensaje != null) {
            response.sendRedirect("registrarCliente.jsp?messageRequired=" + mensaje);
            return;
        }

        try {
            clienteService = (TMantengoClienteService) WebUtil.getService("Cliente");
            clienteService.insertar(cliente);
            mensaje = "Se registro el cliente satisfactoriamente.";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error en el sistema, por favor, vuelva a intentarlo.";
            response.sendRedirect("listarClientes.jsp?messageError=" + mensaje);
            return;
        }
        response.sendRedirect("listarClientes.jsp?messageSuccess=" + mensaje);
    }

    protected void actualizar(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String mensaje = null;
        Cliente cliente = obtenerCliente(request);
        cliente.setClienteId(Integer.parseInt(request.getParameter("txtClienteId")));

        mensaje = WebUtil.validarCliente(cliente);
        if (mensaje != null) {
            response.sendRedirect("modificarCliente.jsp"
                    + "?clienteId=" + request.getParameter("txtClienteId")
                    + "&messageRequired=" + mensaje);
            return;
        }

        try {
            clienteService = (TMantengoClienteService) WebUtil.getService("Cliente");
            clienteService.actualizar(cliente);
            mensaje = "Cliente actualizado satisfactoriamente.";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error del sistema, vuelva a intentarlo en un momento.";
            response.sendRedirect("listarClientes.jsp?messageError=" + mensaje);
            return;
        }
        response.sendRedirect("listarClientes.jsp?messageSuccess=" + mensaje);
        return;
    }

    protected void eliminar(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String mensaje = null;
        try {
            clienteService = (TMantengoClienteService) WebUtil.getService("Cliente");
            clienteService.eliminar(Integer.parseInt(request.getParameter("clienteId")));
            mensaje = "Cliente eliminado satisfactoriamente";
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error del sistema, vuelva a intentarlo en un momento.";
            response.sendRedirect("listarClientes.jsp?messageError=" + mensaje);
            return;
        }
        response.sendRedirect("listarClientes.jsp?messageSuccess=" + mensaje);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("txtAction");
        try {
            if (accion.equalsIgnoreCase("registrar")) {
                registrar(request, response);
            } else if (accion.equalsIgnoreCase("actualizar")) {
                actualizar(request, response);
            } else if (accion.equalsIgnoreCase("eliminar")) {
                eliminar(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarClientes.jsp?messageError="
                    + "Error en la peticion al sistema, vuelva a intentarlo.");
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
