package modelo;

import java.io.Serializable;

public class mensaje implements Serializable {
    String Usuario;
    String Nombre;
    String Mensaje;
    String id;
    String msgRetroalimentado;
    
    public mensaje(String Usuario, String Nombre, String Mensaje, String id, String msgRetroalimentado) {
        this.Usuario = Usuario;
        this.Nombre = Nombre;
        this.Mensaje = Mensaje;
        this.id = id;
        this.msgRetroalimentado = msgRetroalimentado;
    }
    
    public mensaje(String Usuario, String Mensaje, String id, String msgRetroalimentado) {
        this.Usuario = Usuario;
        this.Mensaje = Mensaje;
        this.id = id;
        this.msgRetroalimentado = msgRetroalimentado;
    }

    

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgRetroalimentado() {
        return msgRetroalimentado;
    }

    public void setMsgRetroalimentado(String msgRetroalimentado) {
        this.msgRetroalimentado = msgRetroalimentado;
    }
}
