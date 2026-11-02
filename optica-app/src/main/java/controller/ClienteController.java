package com.optica.opticaapp.controller;

import com.optica.opticaapp.dao.AroDAO;
import com.optica.opticaapp.dao.MedicionDAO;
import com.optica.opticaapp.dao.impl.AroDAOImpl;
import com.optica.opticaapp.dao.impl.MedicionDAOImpl;
import com.optica.opticaapp.modelo.Aro;
import com.optica.opticaapp.modelo.Medicion;
import com.optica.opticaapp.modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClienteController {

    private AroDAO aroDAO = new AroDAOImpl();

    // 1. Mostrar el catálogo (Usa "cata" porque así lo arreglamos antes)
    @GetMapping("/cliente/catalogo")
    public String mostrarCatalogo(Model model) {
        List<Aro> catalogo = aroDAO.listarTodos();
        model.addAttribute("catalogoAros", catalogo);
        return "cata";
    }

    // 2. Apartar el aro y guardarlo en sesión
    @GetMapping("/cliente/apartar/{id}")
    public String apartarAro(@PathVariable int id, HttpSession session) {
        Aro aroSeleccionado = aroDAO.buscarPorId(id);

        // Guardamos el aro en la sesión del usuario
        session.setAttribute("aroApartado", aroSeleccionado);

        // Redirigimos al catálogo con un mensaje de éxito
        return "redirect:/cliente/catalogo?exito";
    }

    // 3. Ver historial de mediciones (Blindado contra errores de sesión)
    @GetMapping("/cliente/historial")
    public String mostrarHistorial(HttpSession session, Model model) {
        // Obtenemos el usuario de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        // Creamos una lista vacía por defecto para que la página nunca falle
        List<Medicion> listaMediciones = new ArrayList<>();

        // Si el usuario existe (está logueado), buscamos sus mediciones en la BD
        if (usuario != null) {
            MedicionDAO medicionDAO = new MedicionDAOImpl();
            listaMediciones = medicionDAO.listarPorCliente(usuario.getId());
        }

        // Enviamos la lista al HTML
        model.addAttribute("mediciones", listaMediciones);

        return "historial";
    }
}