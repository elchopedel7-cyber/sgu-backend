package com.sgu.modelo;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasenaHash;
    private boolean estado;
    private int idRol;

    public Usuario() {}

    public Usuario(String nombre, String correo, String contrasenaHash, boolean estado, int idRol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenaHash = contrasenaHash;
        this.estado = estado;
        this.idRol = idRol;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasenaHash() { return contrasenaHash; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }
}