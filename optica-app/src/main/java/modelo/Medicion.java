package com.optica.opticaapp.modelo;

public class Medicion {
    private int id;
    private int clienteId;
    private double esfera;
    private double cilindro;
    private int eje;
    private double adicion;
    private String fecha;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public double getEsfera() { return esfera; }
    public void setEsfera(double esfera) { this.esfera = esfera; }
    public double getCilindro() { return cilindro; }
    public void setCilindro(double cilindro) { this.cilindro = cilindro; }
    public int getEje() { return eje; }
    public void setEje(int eje) { this.eje = eje; }
    public double getAdicion() { return adicion; }
    public void setAdicion(double adicion) { this.adicion = adicion; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}