<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand">Mantenimiento de Clientes</a>
</div>

<div>
<ul class="nav navbar-right top-nav">
    <div style="color: white; padding: 10px 50px 5px 50px; float: right; font-size: 16px;">
        <a href="SesionController?txtAction=cerrar" class="btn btn-danger square-btn-adjust" id="linkCerrarSesion" 
           onclick="return confirm('¿Seguro que desea cerrar sesion?')"><i class="fa fa-sign-out"></i> Cerrar Sesión</a>
    </div>
    <div style="color: white; padding: 10px 50px 5px 50px; float: right;">
        <a class="navbar-brand">Logeado como root</a>
    </div>
</ul>
</div>