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

import com.pet.entity.Medico;
import com.pet.service.MedicoService;

@Controller
@RequestMapping("/gestionmedico")
public class MedicoController {

    @Autowired
    private MedicoService serviceMedico;

    // 1. Método para Listar y preparar la pantalla de mantenimiento
    @GetMapping("/lista")
    public String listadoMedicos(Model model) {
        // Título de la interfaz de usuario
        model.addAttribute("mensaje", "Bienvenido al módulo de Gestión de Médicos Veterinarios");
        
        // Enviamos la lista de médicos para llenar la tabla principal
        model.addAttribute("medicos", serviceMedico.listar());
        
        // Molde vacío que Thymeleaf mapeará en el modal de registro
        Medico medico = new Medico();
        model.addAttribute("medico", medico);
        
        // Retorna la ruta física de la vista: src/main/resources/templates/medico/mantMedicos.html
        return "medico/mantMedicos";
    }

    // 2. Método para Registrar un nuevo veterinario
    @PostMapping("/registrar")
    public String registrarMedico(Medico medico, RedirectAttributes redirect) {
        medico.setEstado("ACTIVO");
        
        serviceMedico.registrar(medico);
        
        // Mensaje flash de éxito para la alerta de Thymeleaf
        redirect.addFlashAttribute("msg", "Médico registrado correctamente.");
        
        return "redirect:/gestionmedico/lista";
    }

    // 3. Método para Buscar por ID (Retorna JSON para cargar los campos al editar)
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Medico buscarPorId(@PathVariable int id) {
        return serviceMedico.buscarPorId(id);
    }

    // 4. Método para Guardar las modificaciones
    @PostMapping("/actualizar")
    public String actualizarMedico(Medico medico, RedirectAttributes redirect) {
        serviceMedico.actualizar(medico);
        redirect.addFlashAttribute("msg", "Datos del médico actualizados correctamente.");
        return "redirect:/gestionmedico/lista";
    }

    // 5. Método para Desactivar 
    @GetMapping("/desactivar/{id}")
    public String desactivarEstado(@PathVariable int id, RedirectAttributes redirect) {
        serviceMedico.desactivar(id);
        redirect.addFlashAttribute("msg", "Médico inhabilitado correctamente.");
        return "redirect:/gestionmedico/lista";
    }
}