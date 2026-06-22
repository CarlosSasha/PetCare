package com.pet.service;

import java.util.List;
import com.pet.entity.Medico;

public interface MedicoService {
    List<Medico> listar();
    void registrar(Medico medico);
    void actualizar(Medico medico);
    void desactivar(int id);
    Medico buscarPorId(int id);
}