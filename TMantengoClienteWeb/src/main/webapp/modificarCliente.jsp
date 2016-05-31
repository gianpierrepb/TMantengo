
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pe.edu.upc.util.WebUtil"%>
<%@page import="pe.edu.upc.service.TMantengoClienteService"%>
<%@page import="pe.edu.upc.model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cliente cliente = null;
    try {
        TMantengoClienteService clienteService = (TMantengoClienteService) WebUtil.getService("Cliente");
        cliente = clienteService.obtener(Integer.parseInt(request.getParameter("clienteId")));
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("listarClientes.jsp?messageError=Error en el sistema, por favor, vuelva a intentarlo.");
        return;
    }
    String[] arraySexo = WebUtil.getArraySexo();
    String[] arrayEstudios = WebUtil.getArrayNivelEstudios();
%>

<!DOCTYPE html>
<html manifest="manifest.appcache">

    <head>
        <meta http-equiv="X-FRAME-OPTIONS" content="DENY">
        <%@include file="templates/metadata.jsp" %>
        <title>Mantenimiento</title>
        <%@include file="templates/styles.jsp" %>
    </head>
    <body>
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <%@include file="templates/header.jsp" %>
            </nav>

            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Mantenimiento de Clientes
                            </h1>
                        </div>
                    </div>

                    <%@include file="templates/messages.jsp" %>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-edit"></i> Modificar Cliente</h3>
                        </div>
                        <div class="panel-body">
                            <form action="ClienteController" method="post">
                                <input type="hidden" name="txtAction" value="actualizar" />
                                <input type="hidden" name="txtClienteId" value="<%= cliente.getIdCliente() %>" />
                                <br />
                                <div class="form-group">
                                    <label>&nbsp;ID</label>
                                    <input class="form-control" type="text" id="txtIdCliente" name="txtIdCliente" placeholder="ID" value="<%= cliente.getIdCliente() %>" readonly/>
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;Nombre</label>
                                    <input class="form-control" type="text" id="txtNombre" name="txtNombre" placeholder="Nombre" value="<%= cliente.getNombre() %>" />
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;Apellido</label>
                                    <input class="form-control" type="text" id="txtApellido" name="txtApellido" placeholder="Apellido" value="<%= cliente.getApellido() %>" />
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;DNI</label>
                                    <input class="form-control" type="text" id="txtDni" name="txtDni" placeholder="DNI" value="<%= cliente.getDni() %>" />
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;Edad</label>
                                    <input class="form-control" type="text" id="txtDni" name="txtEdad" placeholder="Edad" value="<%= cliente.getEdad() %>" />
                                </div>
                                <div class="form-group">
                                    <label>Sexo</label>
                                    <br />
                                    <%
                                        for(int i = 0; i < arraySexo.length; i+=2)
                                        {
                                            if(arraySexo[i].equals(cliente.getSexo()))
                                            {
                                    %>
                                                <label class="radio-inline">
                                                    <input type="radio" name="rdoSexo" id="rdoSexo" value="<%= arraySexo[i] %>" checked="checked"><%= arraySexo[i + 1] %>
                                                </label>
                                    <% 
                                            }else{
                                    %>
                                                <label class="radio-inline">
                                                    <input type="radio" name="rdoSexo" id="rdoSexo" value="<%= arraySexo[i] %>"><%= arraySexo[i + 1] %>
                                                </label>
                                    <%
                                            }
                                        }
                                    %>
                                </div>
                                
                                <div class="form-group">
                                <label>Nivel de Estudios</label>
                                    <select class="form-control" id="cboEstudios" name="cboEstudios">
                                        <%
                                            for(int i = 0; i < arrayEstudios.length; i += 2)
                                            {
                                                if(arrayEstudios[i].equals(cliente.getNivelEstudios()))
                                                {
                                        %>
                                                    <option value="<%= arrayEstudios[i] %>" selected="selected">
                                                        <%= arrayEstudios[i + 1] %>
                                                    </option>
                                        <% 
                                                }else{
                                        %>
                                                    <option value="<%= arrayEstudios[i] %>">
                                                        <%= arrayEstudios[i + 1] %>
                                                    </option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;Telefono</label>
                                    <input class="form-control" type="text" id="txtDni" name="txtTelefono" placeholder="Telefono" value="<%= cliente.getTelefono() %>" />
                                </div>
                                <br />
                                <button type="submit" id="btnGuardar" class="btn btn-primary square-btn-adjust" onclick="return confirm('¿Está seguro de actualizar este cliente?')"><i></i> Guardar</button>
                                <a href="listarClientes.jsp" class="btn btn-primary square-btn-adjust"><i></i> Cancelar</a>
                                <br /><br />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="templates/scripts.jsp" %>
    </body>
</html>