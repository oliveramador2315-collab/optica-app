package com.optica.opticaapp.modelo;

public class Usuario {
    private int id;
    private String correo;
    private String password_hash;
    private String rol;

    // Constructor vacío
    public Usuario() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword_hash() { return password_hash; }
    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}