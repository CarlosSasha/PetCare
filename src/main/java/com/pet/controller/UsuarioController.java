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

import com.pet.entity.Rol;
import com.pet.entity.Usuario;
import com.pet.service.RolService;
import com.pet.service.UsuarioService;

@Controller
@RequestMapping("/gestionusuario")
public class UsuarioController {

    @Autowired
    private UsuarioService serviceUsuario;

    // Añadi el servicio de Rol para llenar el combobox en la vista HTML
    @Autowired
    private RolService serviceRol;

    // 1. Método Listar
    @GetMapping("/lista")
    public String listadoUsuarios(Model model) {
        model.addAttribute("mensaje", "Bienvenido al módulo de Gestión de Usuarios");
        
        // Enviamos la lista de usuarios para la tabla principal
        model.addAttribute("usuarios", serviceUsuario.listar());
        
        // Enviamos la lista de roles para el `<select>` del formulario de registro
        model.addAttribute("roles", serviceRol.listar());
        
        // Objeto vacío para registrar
        Usuario usuario = new Usuario();
        // Inicializamos el objeto Rol dentro del Usuario 
        usuario.setRol(new Rol()); 
        
        model.addAttribute("usuario", usuario);
        
        return "usuario/mantUsuarios";
    }

    // 2. Método Registrar
    @PostMapping("/registrar")
    public String registrarUsuario(Usuario usuario, RedirectAttributes redirect) {
        
        
        serviceUsuario.registrar(usuario);
        redirect.addFlashAttribute("msg", "Usuario registrado exitosamente en el sistema.");
        return "redirect:/gestionusuario/lista";
    }

    // 3. Método Buscar por ID (Devuelve JSON para el Modal de Editar)
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Usuario buscarPorId(@PathVariable int id) {
        return serviceUsuario.buscarPorId(id);
    }

    // 4. Método Actualizar
    @PostMapping("/actualizar")
    public String actualizarUsuario(Usuario usuario, RedirectAttributes redirect) {
        serviceUsuario.actualizar(usuario);
        redirect.addFlashAttribute("msg", "Datos del usuario actualizados correctamente.");
        return "redirect:/gestionusuario/lista";
    }

    // 5. Método Desactivar
    @GetMapping("/desactivar/{id}")
    public String desactivarEstado(@PathVariable int id, RedirectAttributes redirect) {
        serviceUsuario.desactivar(id);
        redirect.addFlashAttribute("msg", "El acceso del usuario ha sido inhabilitado.");
        return "redirect:/gestionusuario/lista";
    }
}