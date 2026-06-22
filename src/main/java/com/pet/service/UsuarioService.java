package com.pet.service;

import java.util.List;
import com.pet.entity.Usuario;

public interface UsuarioService {
	
	//Metodos
	List<Usuario> listar();
    void registrar(Usuario usuario);
    void actualizar(Usuario usuario);
    void desactivar(int id);    
    Usuario buscarPorId(int id);

 // NUEVO MÉTODO PARA EL LOGIN
    public Usuario validarIngreso(String username, String password);
}
