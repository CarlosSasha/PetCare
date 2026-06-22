package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.entity.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{
	
	Usuario findByUsernameAndPassword(String username, String password);
}
