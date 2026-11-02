package com.optica.opticaapp.dao.impl;

import com.optica.opticaapp.config.ConexionDB;
import com.optica.opticaapp.dao.UsuarioDAO;
import com.optica.opticaapp.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario validarLogin(String correo, String password) {
        Usuario usuario = null;
        // Buscamos coincidencia en correo y contraseña
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password_hash = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setPassword_hash(rs.getString("password_hash"));
                    usuario.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar login: " + e.getMessage());
        }
        return usuario;
    }

    @Override
    public void insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (correo, password_hash, rol) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getCorreo());
            pstmt.setString(2, usuario.getPassword_hash());
            pstmt.setString(3, usuario.getRol());

            pstmt.executeUpdate();
            System.out.println("✅ Usuario registrado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
        }
    }
}