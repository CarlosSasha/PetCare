package com.pet.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pet.entity.Cliente;
import com.pet.service.ClienteService;

@Controller
@RequestMapping("/gestioncliente")
public class ClienteController {

    @Autowired
    private ClienteService serviceCliente;

    // 1. Método para Listar y preparar la pantalla
    @GetMapping("/lista")
    public String listadoClientes(Model model) {
        // Mensaje de cabecera para la vista
        model.addAttribute("mensaje", "Bienvenido al módulo de Gestión de Clientes");
        
        // Enviamos la lista completa de clientes a la tabla HTML
        model.addAttribute("clientes", serviceCliente.listar());
        
        // Molde de objeto vacío requerido por Thymeleaf para el formulario de registro
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        
        // Retorna la ruta física del archivo HTML: src/main/resources/templates/cliente/mantClientes.html
        return "cliente/mantClientes";
    }

    // 2. Método para Registrar
    @PostMapping("/registrar")
    public String registrarCliente(Cliente cliente, RedirectAttributes redirect) {
        cliente.setEstado("ACTIVO");
        
        serviceCliente.registrar(cliente);
        
        // Mensaje flash que se destruye tras mostrarse en la pantalla
        redirect.addFlashAttribute("msg", "Cliente registrado correctamente.");
        
        // Redirección interna al método de listado para refrescar la pantalla
        return "redirect:/gestioncliente/lista";
    }

    // 3. Método para Buscar por ID 
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Cliente buscarPorId(@PathVariable int id) {
        return serviceCliente.buscarPorId(id);
    }

    // 4. Método para Actualizar
    @PostMapping("/actualizar")
    public String actualizarCliente(Cliente cliente, RedirectAttributes redirect) {
        // Mantenemos el estado actual al actualizar o lo recuperamos del formulario
        serviceCliente.actualizar(cliente);
        redirect.addFlashAttribute("msg", "Datos del cliente actualizados correctamente.");
        return "redirect:/gestioncliente/lista";
    }

    // 5. Método para Desactivar (Eliminación Lógica)
    @GetMapping("/desactivar/{id}")
    public String desactivarEstado(@PathVariable int id, RedirectAttributes redirect) {
        serviceCliente.desactivar(id);
        redirect.addFlashAttribute("msg", "Cliente inhabilitado correctamente.");
        return "redirect:/gestioncliente/lista";
    }
}
