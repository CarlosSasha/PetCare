package com.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//class e interfaz

import com.pet.entity.Usuario;
// Repository
import com.pet.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repoUsuario;

	@Override
	public List<Usuario> listar() {
		return repoUsuario.findAll();
	}

	@Override
    public void registrar(Usuario usuario) {
        repoUsuario.save(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) {
        repoUsuario.save(usuario);
    }

    @Override
    public void desactivar(int id) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            u.setEstado("INACTIVO");
            repoUsuario.save(u);
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        return repoUsuario.findById(id).orElse(null);
    }

    @Override
    public Usuario validarIngreso(String username, String password) {
        // Llamamos al método corregido
        return repoUsuario.findByUsernameAndPassword(username, password);
    }

}
