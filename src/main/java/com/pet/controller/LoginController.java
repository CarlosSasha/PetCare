package com.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pet.entity.Usuario;
import com.pet.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/inicioSesion")
public class LoginController {

    @Autowired
    private UsuarioService serviceUsuario;

    // 1. Mostrar la pantalla de Login
    @GetMapping("/login")
    public String mostrarPaginaLogin() {
        // Retorna la vista HTML del login (src/main/resources/templates/sesion/login.html)
        return "sesion/login"; 
    }

    // 2. Validar las credenciales enviadas desde el formulario HTML
    @PostMapping("/validar")
    public String validarAcceso(@RequestParam("username") String username, 
                                @RequestParam("password") String password, 
                                HttpSession session, 
                                RedirectAttributes redirect) {
        
        // Consulta a la base de datos
        Usuario objUsuario = serviceUsuario.validarIngreso(username, password);
        
        if (objUsuario != null) {
            // Si el estado es "INACTIVO", se podría bloquear aquí
            session.setAttribute("usuarioLogueado", objUsuario);
            return "redirect:/gestioncita/lista"; 
        } else {
            redirect.addFlashAttribute("error", "Usuario o contraseña incorrectos. Intente nuevamente.");
            return "redirect:/login";
        }
    }

    // 3. Cerrar Sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session, RedirectAttributes redirect) {
        // Destruye la memoria de la sesión actual
        session.invalidate();
        
        redirect.addFlashAttribute("msg", "Has cerrado sesión correctamente.");
        return "redirect:/login";
    }
}