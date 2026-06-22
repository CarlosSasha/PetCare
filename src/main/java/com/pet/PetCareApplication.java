package com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//clase y interzas
import com.pet.entity.Usuario;
import com.pet.service.UsuarioService;

@SpringBootApplication
//implementar commandlinerunner y su metodo
public class PetCareApplication implements CommandLineRunner{ 
	
	//Prueba
	@Autowired
	UsuarioService serviceUsuario;

	public static void main(String[] args) {
		SpringApplication.run(PetCareApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("----------------------");
		System.out.println("Listado de Usuarios");
		System.out.println("----------------------");
		serviceUsuario.listar().forEach(c->{
			System.out.println(c.getIdUsuario() + " - " + 
								c.getRol().getNombre()+ " - " +
								c.getUsername()+ " - " +
								c.getPassword()+ " - " +
								c.getEstado()
								);
		});
		
	}

}
