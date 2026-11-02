package com.optica.opticaapp.modelo;

public class Aro {
    private int id;
    private String marca;
    private String material;
    private String forma;
    private double precio;
    private int stock;

    public Aro() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public String getForma() { return forma; }
    public void setForma(String forma) { this.forma = forma; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}