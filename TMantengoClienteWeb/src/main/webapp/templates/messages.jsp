<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String mensajeError = null;
    String mensajeSuccess = null;
    String mensajeRequired = null;
    
    if (request.getParameter("messageError") != null) {
        mensajeError = request.getParameter("messageError");
    }
    if (request.getParameter("messageSuccess") != null) {
        mensajeSuccess = request.getParameter("messageSuccess");
    }
    if (request.getParameter("messageRequired") != null) {
        mensajeRequired = request.getParameter("messageRequired");
    }
%>

<%
    if (mensajeError != null)
    {                        
%>
        <div class="alert alert-danger" id="mensajeError">
            <c:out value="<%= mensajeError %>"/>
        </div>
<%
    }
%>

<%
    if (mensajeSuccess != null)
    {                        
%>
        <div class="alert alert-success fade in" id="mensajeExito">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <c:out value="<%= mensajeSuccess %>"/>
        </div>
<%
    }
%>

<%
    if (mensajeRequired != null)
    {                        
%>
        <div class="alert alert-danger" id="mensajeRequired">
            <c:out value= "<%= mensajeRequired %>" />
        </div>
<%
    }
%>
