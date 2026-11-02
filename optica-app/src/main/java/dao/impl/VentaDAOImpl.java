package com.optica.opticaapp.dao.impl;

import com.optica.opticaapp.config.ConexionDB;
import com.optica.opticaapp.dao.VentaDAO;
import com.optica.opticaapp.modelo.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaDAOImpl implements VentaDAO {
    @Override
    public void registrarVenta(Venta venta) {
        String sql = "INSERT INTO ventas (cliente_id, medicion_id, aro_id, subtotal, impuesto, total) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, venta.getClienteId());
            pstmt.setInt(2, venta.getMedicionId());
            pstmt.setInt(3, venta.getAroId());
            pstmt.setDouble(4, venta.getSubtotal());
            pstmt.setDouble(5, venta.getImpuesto());
            pstmt.setDouble(6, venta.getTotal());

            pstmt.executeUpdate();
            System.out.println(" Venta registrada en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al registrar venta: " + e.getMessage());
        }
    }
}