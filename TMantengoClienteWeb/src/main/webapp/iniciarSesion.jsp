
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html manifest="manifest.appcache">
    
    <head>
       
        
        <%@include file="templates/metadata.jsp" %>
        
        <title>Iniciar Sesion</title>
        <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    </head>
    <body>
        <div class="container">
            <div class="row text-center ">
                <div class="col-md-12">
                    <br /><br /><br /><br />
                </div>
            </div>
            <div class="row ">
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                    <div class="panel panel-default">
                        <div class="panel-heading" align="center">
                            <strong>Login</strong>  
                        </div>
                        <div class="panel-body">
                            <form action="SesionController" method="post">
                                <input type="hidden" name="txtAction" value="iniciar" />
                                <br>
                                <div class="form-group input-group">
                                    <span class="input-group-addon">Usuario</span>
                                    <input type="text" class="form-control" name="txtUsuario" id="txtUsuario" placeholder="Usuario">
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon">Password</span>
                                    <input type="password" class="form-control" name="txtPassword" id="txtPassword" placeholder="Contraseña">
                                </div>
                                <div align="center">
                                    <button type="submit" class="btn btn-primary square-btn-adjust" id="btnIniciarSesion"><i class="fa fa-sign-in"></i> Iniciar Sesión</button>
                                </div>
                                <hr>
                                <%@include file="templates/messages.jsp" %>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
