package pe.edu.upc.bean;

import java.util.Date;

public class Usuario {
    
    private String usuario;
    private Date fechaInicioSesion;
    
    public Usuario () {       
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario (String usuario) {
        this.usuario = usuario;
    }
    
    public Date getFechaInicioSesion() {
        return fechaInicioSesion;
    }
    
    public void setFechaInicioSesion(Date fechaInicioSesion) {
        this.fechaInicioSesion = fechaInicioSesion;
    }
}
