package com.optica.opticaapp.dao.impl;

import com.optica.opticaapp.config.ConexionDB;
import com.optica.opticaapp.dao.AroDAO;
import com.optica.opticaapp.modelo.Aro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AroDAOImpl implements AroDAO {

    @Override
    public void insertar(Aro aro) {
        String sql = "INSERT INTO aros (marca, material, forma, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, aro.getMarca());
            pstmt.setString(2, aro.getMaterial());
            pstmt.setString(3, aro.getForma());
            pstmt.setDouble(4, aro.getPrecio());
            pstmt.setInt(5, aro.getStock());

            pstmt.executeUpdate();
            System.out.println("✅ Aro insertado correctamente: " + aro.getMarca());

        } catch (SQLException e) {
            System.err.println("Error al insertar aro: " + e.getMessage());
        }
    }

    @Override
    public List<Aro> listarTodos() {
        List<Aro> lista = new ArrayList<>();
        String sql = "SELECT * FROM aros";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Aro aro = new Aro();
                aro.setId(rs.getInt("id"));
                aro.setMarca(rs.getString("marca"));
                aro.setMaterial(rs.getString("material"));
                aro.setForma(rs.getString("forma"));
                aro.setPrecio(rs.getDouble("precio"));
                aro.setStock(rs.getInt("stock"));
                lista.add(aro);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar aros: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Aro buscarPorId(int id) {
        Aro aro = null;
        String sql = "SELECT * FROM aros WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    aro = new Aro();
                    aro.setId(rs.getInt("id"));
                    aro.setMarca(rs.getString("marca"));
                    aro.setMaterial(rs.getString("material"));
                    aro.setForma(rs.getString("forma"));
                    aro.setPrecio(rs.getDouble("precio"));
                    aro.setStock(rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar aro: " + e.getMessage());
        }
        return aro;
    }

    @Override
    public void actualizarStock(int id, int nuevoStock) {
        String sql = "UPDATE aros SET stock = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, nuevoStock);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar stock: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM aros WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Aro eliminado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al eliminar aro: " + e.getMessage());
        }
    }

    @Override
    public List<Aro> listarStockBajo(int limite) {
        List<Aro> lista = new ArrayList<>();
        String sql = "SELECT * FROM aros WHERE stock < ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limite);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Aro aro = new Aro();
                    aro.setId(rs.getInt("id"));
                    aro.setMarca(rs.getString("marca"));
                    aro.setMaterial(rs.getString("material"));
                    aro.setForma(rs.getString("forma"));
                    aro.setPrecio(rs.getDouble("precio"));
                    aro.setStock(rs.getInt("stock"));
                    lista.add(aro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar stock bajo: " + e.getMessage());
        }
        return lista;
    }@Override
    public void actualizar(Aro aro) {
        String sql = "UPDATE aros SET marca=?, material=?, forma=?, precio=?, stock=? WHERE id=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, aro.getMarca());
            pstmt.setString(2, aro.getMaterial());
            pstmt.setString(3, aro.getForma());
            pstmt.setDouble(4, aro.getPrecio());
            pstmt.setInt(5, aro.getStock());
            pstmt.setInt(6, aro.getId());

            pstmt.executeUpdate();
            System.out.println(" Aro actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar aro: " + e.getMessage());
        }
    }
}