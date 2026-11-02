package com.optica.opticaapp.controller;

import com.optica.opticaapp.dao.AroDAO;
import com.optica.opticaapp.dao.impl.AroDAOImpl;
import com.optica.opticaapp.modelo.Aro;
import com.optica.opticaapp.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VendedorController {

    private AroDAO aroDAO = new AroDAOImpl();
    private VentaService ventaService = new VentaService();

    // 1. Mostrar el Inventario
    @GetMapping("/vendedor/inventario")
    public String mostrarInventario(Model model) {
        List<Aro> todosLosAros = aroDAO.listarTodos();
        List<Aro> arosBajos = aroDAO.listarStockBajo(5);

        model.addAttribute("listaAros", todosLosAros);
        model.addAttribute("arosBajos", arosBajos);

        return "inventario";
    }

    // 2. Formulario para crear un aro nuevo
    @GetMapping("/vendedor/aros/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("aro", new Aro());
        return "formularioAro";
    }

    // 3. Guardar el aro nuevo en la BD
    @PostMapping("/vendedor/aros/guardar")
    public String guardarAro(@ModelAttribute Aro aro) {
        aroDAO.insertar(aro);
        return "redirect:/vendedor/inventario";
    }

    // 4. Formulario para editar un aro
    @GetMapping("/vendedor/aros/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Aro aro = aroDAO.buscarPorId(id);
        model.addAttribute("aro", aro);
        return "formularioAro";
    }

    // 5. Guardar los cambios del aro editado
    @PostMapping("/vendedor/aros/actualizar")
    public String actualizarAro(@ModelAttribute Aro aro) {
        aroDAO.actualizar(aro);
        return "redirect:/vendedor/inventario";
    }

    // 6. Eliminar un aro
    @GetMapping("/vendedor/aros/eliminar/{id}")
    public String eliminarAro(@PathVariable int id) {
        aroDAO.eliminar(id);
        return "redirect:/vendedor/inventario";
    }

    // ==========================================
    // NUEVOS MÉTODOS PARA EL MÓDULO DE VENTAS
    // ==========================================

    // 7. Mostrar la pantalla de ventas
    @GetMapping("/vendedor/ventas")
    public String mostrarFormularioVenta(Model model) {
        model.addAttribute("listaAros", aroDAO.listarTodos());
        return "venta";
    }

    // 8. Procesar la venta (Calcular totales y restar stock)
    @PostMapping("/vendedor/ventas/procesar")
    public String procesarVenta(@RequestParam int clienteId, @RequestParam int medicionId,
                                @RequestParam int aroId, @RequestParam int cantidad, Model model) {

        String resultado = ventaService.procesarVenta(clienteId, medicionId, aroId, cantidad);

        // Enviamos el mensaje de éxito o error al HTML
        model.addAttribute("mensaje", resultado);
        // Volvemos a cargar la lista de aros por si el stock cambió
        model.addAttribute("listaAros", aroDAO.listarTodos());

        return "venta";
    }
}