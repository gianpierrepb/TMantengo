
<%@page import="java.util.List"%>
<%@page import="pe.edu.upc.util.WebUtil"%>
<%@page import="pe.edu.upc.service.TMantengoClienteService"%>
<%@page import="pe.edu.upc.model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String mensaje = null;
    List<Cliente> lista = null;
    try {
        TMantengoClienteService clienteService = (TMantengoClienteService) WebUtil.getService("Cliente");
        lista = clienteService.listar();
    } catch (Exception e) {
        e.printStackTrace();
        mensaje = "Error en el sistema, por favor, vuelva a intentarlo.";
    }
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
                        <div class="col-md-12">
                            <h1 class="page-header">
                                Lista de clientes
                            </h1>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-users"></i>Clientes registrados</h3>
                        </div>
                        <div class="panel-body">
                            <%
                                if(mensaje != null)
                                {
                            %>
                            <div class="alert alert-info">
                                <h3>  <c:out value="${mensaje}" />  </h3>
                            </div>
                            <%
                                } else {
                            %>
                                <%
                                    if(lista.size() > 0)
                                    {
                                %>
                                <input id="searchInput" class="form-control" placeholder="Buscar" />
                            
                                    <br />
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Apellido</th>
                                                        <th>Nombre</th>
                                                        <th>DNI</th>
                                                        <th>Edad</th>
                                                        <th>Sexo</th>
                                                        <th>Nivel de estudios</th>
                                                        <th>Telefono</th>
                                                        <th colspan="2">Opciones</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="toBody">
                                                    <%
                                                        for(Cliente item : lista)
                                                        {
                                                    %>
                                                            <tr>
                                                                <td><c:out value="<%= item.getApellido() %>"  /></td>
                                                                <td><c:out value="<%= item.getNombre() %>"  /></td>
                                                                <td><c:out value="<%= item.getDni() %>"/></td>
                                                                <td><c:out value="<%= item.getEdad() %>"/></td>
                                                                <td><c:out value="<%= item.getSexo() %>"/></td>
                                                                <td><c:out value="<%= item.getNivelEstudios()%>"/></td>
                                                                <td><c:out value="<%= item.getTelefono() %>"/></td>
                                                                <td>
                                                                    <%
                                                                        String urlAct = "modificarCliente.jsp";
                                                                        urlAct += "?clienteId=" + item.getIdCliente();
                                                                    %>
                                                                    <a href="<%= urlAct %>" id="modificar<%= item.getIdCliente()%>" class="btn btn-primary square-btn-adjust"><i class="fa fa-edit"></i> Editar</a>
                                                                </td>
                                                                <td>
                                                                    <%
                                                                        String urlEli = "ClienteController?txtAction=eliminar";
                                                                        urlEli += "&clienteId=" + item.getIdCliente();
                                                                    %>
                                                                    <a href="<%= urlEli %>" id="eliminar<%= item.getIdCliente()%>" class="btn btn-primary square-btn-adjust" onclick="return confirm('¿Está seguro de eliminar este cliente?')" ><i class="fa fa-times"></i> Eliminar</a> 
                                                                </td>
                                                            </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                <%
                                    } else {
                                %>
                                        <div id="mensajeListar" class="alert alert-info">
                                            <h3>No existen clientes registrados</h3>
                                        </div>
                                <%
                                    }
                                %>
                                <br />
                                <a href="registrarCliente.jsp" class="btn btn-primary square-btn-adjust" id="btnAgregar"><i class="fa fa-plus"></i> Agregar nuevo cliente</a>
                            <%
                                }
                            %>
                        </div>
                        <%@include file="templates/messages.jsp" %>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="templates/scripts.jsp" %>
    </body>
</html>