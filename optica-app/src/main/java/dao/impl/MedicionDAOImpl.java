package com.optica.opticaapp.dao.impl;

import com.optica.opticaapp.config.ConexionDB;
import com.optica.opticaapp.dao.MedicionDAO;
import com.optica.opticaapp.modelo.Medicion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicionDAOImpl implements MedicionDAO {
    @Override
    public List<Medicion> listarPorCliente(int clienteId) {
        List<Medicion> lista = new ArrayList<>();
        String sql = "SELECT * FROM mediciones WHERE cliente_id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clienteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Medicion m = new Medicion();
                    m.setId(rs.getInt("id"));
                    m.setClienteId(rs.getInt("cliente_id"));
                    m.setEsfera(rs.getDouble("esfera"));
                    m.setCilindro(rs.getDouble("cilindro"));
                    m.setEje(rs.getInt("eje"));
                    m.setAdicion(rs.getDouble("adicion"));
                    m.setFecha(rs.getString("fecha"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar mediciones: " + e.getMessage());
        }
        return lista;
    }
}