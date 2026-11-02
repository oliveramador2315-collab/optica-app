package com.optica.opticaapp.controller;

import com.optica.opticaapp.dao.UsuarioDAO;
import com.optica.opticaapp.dao.impl.UsuarioDAOImpl;
import com.optica.opticaapp.modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    // Muestra la página de login cuando entras a la raíz (localhost:8080/)
    @GetMapping("/")
    public String mostrarLogin() {
        return "login"; // Busca el archivo login.html en templates
    }

    // Procesa el formulario cuando le das clic a "Ingresar"
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        Usuario usuario = usuarioDAO.validarLogin(correo, password);

        if (usuario != null) {
            // Guardamos al usuario en la sesión para saber quién está conectado
            session.setAttribute("usuarioLogueado", usuario);

            // Redirigimos según el rol
            if (usuario.getRol().equals("CLIENTE")) {
                return "redirect:/cliente/catalogo"; // (Lo crearemos luego)
            } else {
                return "redirect:/vendedor/inventario"; // (Lo crearemos luego)
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; // Se queda en la misma página mostrando el error
        }
    }
}