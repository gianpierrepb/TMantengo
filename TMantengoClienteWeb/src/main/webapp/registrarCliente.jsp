
<%@page import="pe.edu.upc.util.WebUtil"%>
<%@page import="pe.edu.upc.service.TMantengoClienteService"%>
<%@page import="pe.edu.upc.model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
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
                                Administrar Clientes
                            </h1>
                        </div>
                    </div>

                    <%@include file="templates/messages.jsp" %>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><i class="fa fa-plus-circle"></i> Agregar Cliente</h3>
                        </div>
                        <div class="panel-body">
                            <form action="ClienteController" method="post">
                                <input type="hidden" name="txtAction" value="registrar" />
                                <br />
                                <div class="form-group">
                                    <label>&nbsp;Nombre</label>
                                    <input class="form-control" type="text" name="txtNombre" id="txtNombre" placeholder="Nombre" />
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;Apellido</label>
                                    <input class="form-control" type="text" name="txtApellido" id="txtApellido" placeholder="Apellido" />
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;DNI</label>
                                    <input class="form-control" type="text" name="txtDni" id="txtDni" placeholder="DNI" />
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;Edad</label>
                                    <input class="form-control" type="text" name="txtEdad" id="txtDni" placeholder="Edad" />
                                </div>
                                <div class="form-group">
                                    <label>Sexo</label>
                                    <br />
                                    <%
                                        for(int i = 0; i < arraySexo.length; i+=2)
                                        {
                                    %>
                                            <label class="radio-inline">
                                                <input type="radio" name="rdoSexo" id="rdoSexo" value="<%= arraySexo[i] %>"><%= arraySexo[i + 1] %>
                                            </label>
                                    <%
                                        }
                                    %>
                                </div>
                                <div class="form-group">
                                <label>Nivel de Estudios</label>
                                    <select class="form-control" name="cboEstudios" id="cboEstudios">
                                        <option value="SELECCIONE">[-Seleccione-]</option>
                                        <%
                                            for(int i = 0; i < arrayEstudios.length; i += 2)
                                            {
                                        %>
                                                <option value="<%= arrayEstudios[i] %>">
                                                    <%= arrayEstudios[i + 1] %>
                                                </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>&nbsp;Telefono</label>
                                    <input class="form-control" type="text" name="txtTelefono" id="txtDni" placeholder="Telefono" />
                                </div>
                                <br />
                                <button type="submit" class="btn btn-primary square-btn-adjust" id="btnRegistrar" onclick="return confirm('¿Está seguro de registrar este cliente?')"><i></i> Registrar</button>
                                <a href="listarClientes.jsp" class="btn btn-primary square-btn-adjust" id="btnCancelar"><i></i> Cancelar</a>
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
