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

import com.pet.entity.Cita;
import com.pet.entity.Mascota;
import com.pet.entity.Medico;
import com.pet.service.CitaService;
import com.pet.service.MascotaService;
import com.pet.service.MedicoService;

@Controller
@RequestMapping("/gestioncita")
public class CitaController {

    @Autowired
    private CitaService serviceCita;

    // Añadi los servicios de las otras entidades que participan en la cita
    @Autowired
    private MascotaService serviceMascota;

    @Autowired
    private MedicoService serviceMedico;

    // 1. Método Listar (Prepara toda la pantalla)
    @GetMapping("/lista")
    public String listadoCitas(Model model) {
        model.addAttribute("mensaje", "Bienvenido al módulo de Gestión de Citas");
        
        // La tabla principal con el historial de citas
        model.addAttribute("citas", serviceCita.listar());
        
        // Los datos para llenar los <select> (combobox) del formulario
        model.addAttribute("mascotas", serviceMascota.listar());
        model.addAttribute("medicos", serviceMedico.listar());
        
        // El molde vacío para registrar una cita nueva
        Cita cita = new Cita();
        // Inicializamos sus dependencias para que Thymeleaf no lance error
        cita.setMascota(new Mascota());
        cita.setMedico(new Medico());
        
        model.addAttribute("cita", cita);
        
        return "cita/mantCitas";
    }

    // 2. Método Registrar
    @PostMapping("/registrar")
    public String registrarCita(Cita cita, RedirectAttributes redirect) {
        // Toda cita nueva entra automáticamente como PROGRAMADA
        cita.setEstadoCita("PROGRAMADA");
        
        serviceCita.registrar(cita);
        redirect.addFlashAttribute("msg", "Cita programada exitosamente.");
        return "redirect:/gestioncita/lista";
    }

    // 3. Método Buscar por ID (Para cargar el modal de edición)
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Cita buscarPorId(@PathVariable int id) {
        return serviceCita.buscarPorId(id);
    }

    // 4. Método Actualizar
    @PostMapping("/actualizar")
    public String actualizarCita(Cita cita, RedirectAttributes redirect) {
        // Aquí se podría actualizar la fecha, el médico o cambiar el estado a ATENDIDA
        serviceCita.actualizar(cita);
        redirect.addFlashAttribute("msg", "Los datos de la cita han sido actualizados.");
        return "redirect:/gestioncita/lista";
    }

    // 5. Método Cancelar (En lugar de desactivar)
    @GetMapping("/cancelar/{id}")
    public String cancelarCita(@PathVariable int id, RedirectAttributes redirect) {
        serviceCita.cancelarCita(id);
        redirect.addFlashAttribute("msg", "La cita ha sido cancelada.");
        return "redirect:/gestioncita/lista";
    }
}