package com.optica.opticaapp.config;

import com.optica.opticaapp.dao.UsuarioDAO;
import com.optica.opticaapp.dao.impl.UsuarioDAOImpl;
import com.optica.opticaapp.modelo.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = System.getenv("DB_URL") != null
            ? System.getenv("DB_URL")
            : "jdbc:postgresql://ep-twilight-butterfly-b72kglhx-pooler.c-13.us-east-1.aws.neon.tech/neondb?user=neondb_owner&password=npg_7fhlH5TiPmVA&sslmode=require";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            // Probamos el DAO de Usuario
            UsuarioDAO userDAO = new UsuarioDAOImpl();

            System.out.println("--- PROBANDO LOGIN CON CREDENCIALES CORRECTAS ---");
            Usuario user1 = userDAO.validarLogin("vendedor@test.com", "123456");
            if (user1 != null) {
                System.out.println(" Login exitoso. Bienvenido: " + user1.getCorreo() + " | Rol: " + user1.getRol());
            } else {
                System.out.println(" Credenciales incorrectas.");
            }

            System.out.println("--- PROBANDO LOGIN CON CONTRASEÑA INCORRECTA ---");
            Usuario user2 = userDAO.validarLogin("vendedor@test.com", "clave_mala");
            if (user2 == null) {
                System.out.println(" El sistema bloqueó al usuario con contraseña incorrecta (esto es correcto).");
            } else {
                System.out.println(" ERROR: ¡Dejó pasar a alguien con mala contraseña!");
            }

        } catch (Exception e) {
            System.out.println("ERROR EN EL PROCESO: " + e.getMessage());
        }
    }
}