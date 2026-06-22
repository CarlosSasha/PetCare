package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pet.entity.Cliente;
import com.pet.entity.Mascota;
import com.pet.service.ClienteService;
import com.pet.service.MascotaService;

@Controller
@RequestMapping("/gestionmascota")
public class MascotaController {

    @Autowired
    private MascotaService serviceMascota;

    // Añadi el servicio de Cliente para asignar un dueño a la mascota
    @Autowired
    private ClienteService serviceCliente;

    // 1. Método Listar
    @GetMapping("/lista")
    public String listadoMascotas(Model model) {
        model.addAttribute("mensaje", "Bienvenido al módulo de Gestión de Mascotas");
        
        // Lista principal para la tabla HTML
        model.addAttribute("mascotas", serviceMascota.listar());
        
        // Lista de clientes para llenar el combobox (el <select> de dueños)
        model.addAttribute("clientes", serviceCliente.listar());
        
        // Molde vacío para el formulario de registro
        Mascota mascota = new Mascota();
        // Inicializamos el objeto Cliente dentro de la Mascota para evitar errores en Thymeleaf
        mascota.setCliente(new Cliente()); 
        
        model.addAttribute("mascota", mascota);
        
        return "mascota/mantMascotas";
    }

    // 2. Método Registrar
    @PostMapping("/registrar")
    public String registrarMascota(Mascota mascota, RedirectAttributes redirect) {
        mascota.setEstado("ACTIVO");
        
        serviceMascota.registrar(mascota);
        redirect.addFlashAttribute("msg", "Mascota registrada correctamente en el sistema.");
        return "redirect:/gestionmascota/lista";
    }

    // 3. Método Buscar por ID 
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Mascota buscarPorId(@PathVariable int id) {
        return serviceMascota.buscarPorId(id);
    }

    // 4. Método Actualizar
    @PostMapping("/actualizar")
    public String actualizarMascota(Mascota mascota, RedirectAttributes redirect) {
        serviceMascota.actualizar(mascota);
        redirect.addFlashAttribute("msg", "Datos de la mascota actualizados exitosamente.");
        return "redirect:/gestionmascota/lista";
    }

    // 5. Método Desactivar 
    @GetMapping("/desactivar/{id}")
    public String desactivarEstado(@PathVariable int id, RedirectAttributes redirect) {
        serviceMascota.desactivar(id);
        redirect.addFlashAttribute("msg", "El registro de la mascota ha sido inhabilitado.");
        return "redirect:/gestionmascota/lista";
    }
}