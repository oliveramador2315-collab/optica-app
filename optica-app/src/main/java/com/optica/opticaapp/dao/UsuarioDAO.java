package com.optica.opticaapp.dao;

import com.optica.opticaapp.modelo.Usuario;

public interface UsuarioDAO {
    // Busca un usuario por correo y contraseña. Si lo encuentra, lo devuelve. Si no, devuelve null.
    Usuario validarLogin(String correo, String password);

    // Para registrar un usuario nuevo
    void insertar(Usuario usuario);
}