package com.optica.opticaapp.modelo;

public class Venta {
    private int id;
    private int clienteId;
    private int medicionId;
    private int aroId;
    private double subtotal;
    private double impuesto;
    private double total;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public int getMedicionId() { return medicionId; }
    public void setMedicionId(int medicionId) { this.medicionId = medicionId; }
    public int getAroId() { return aroId; }
    public void setAroId(int aroId) { this.aroId = aroId; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getImpuesto() { return impuesto; }
    public void setImpuesto(double impuesto) { this.impuesto = impuesto; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}