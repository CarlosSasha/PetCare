package com.pet.service;

import java.util.List;
import com.pet.entity.Cita;

public interface CitaService {
    List<Cita> listar();
    void registrar(Cita cita);
    void actualizar(Cita cita);
    void cancelarCita(int id); 
    Cita buscarPorId(int id);
}