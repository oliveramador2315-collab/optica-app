package com.optica.opticaapp.service;

import com.optica.opticaapp.dao.AroDAO;
import com.optica.opticaapp.dao.VentaDAO;
import com.optica.opticaapp.dao.impl.AroDAOImpl;
import com.optica.opticaapp.dao.impl.VentaDAOImpl;
import com.optica.opticaapp.modelo.Aro;
import com.optica.opticaapp.modelo.Venta;

public class VentaService {

    private AroDAO aroDAO = new AroDAOImpl();
    private VentaDAO ventaDAO = new VentaDAOImpl();

    public String procesarVenta(int clienteId, int medicionId, int aroId, int cantidad) {
        // 1. Buscar el aro para obtener su precio real y su stock
        Aro aro = aroDAO.buscarPorId(aroId);

        if (aro == null) return " Error: El aro no existe.";
        if (aro.getStock() < cantidad) return " Error: No hay suficiente stock (Disponibles: " + aro.getStock() + ").";

        // 2. Calcular totales (usaremos un impuesto del 15% de ejemplo)
        double subtotal = aro.getPrecio() * cantidad;
        double impuesto = subtotal * 0.15;
        double total = subtotal + impuesto;

        // 3. Crear el objeto Venta y guardarlo
        Venta venta = new Venta();
        venta.setClienteId(clienteId);
        venta.setMedicionId(medicionId);
        venta.setAroId(aroId);
        venta.setSubtotal(subtotal);
        venta.setImpuesto(impuesto);
        venta.setTotal(total);

        ventaDAO.registrarVenta(venta);

        // 4. RESTAR EL STOCK AUTOMÁTICAMENTE
        int nuevoStock = aro.getStock() - cantidad;
        aroDAO.actualizarStock(aroId, nuevoStock);

        return " Venta Exitosa. Total a pagar: $" + total + " (Stock actualizado a " + nuevoStock + ")";
    }
}