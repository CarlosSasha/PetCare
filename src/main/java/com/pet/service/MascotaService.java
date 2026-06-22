package com.pet.service;

import java.util.List;
import com.pet.entity.Mascota;

public interface MascotaService {
	List<Mascota> listar();
    void registrar(Mascota mascota);
    void actualizar(Mascota mascota);
    void desactivar(int id);
    Mascota buscarPorId(int id);
}
