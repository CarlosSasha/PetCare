package com.pet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.entity.Cita;
import com.pet.repository.CitaRepository;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository repoCita;

    @Override
    public List<Cita> listar() {
        return repoCita.findAll();
    }

    @Override
    public void registrar(Cita cita) {
        repoCita.save(cita);
    }

    @Override
    public void actualizar(Cita cita) {
        repoCita.save(cita);
    }

    @Override
    public void cancelarCita(int id) {
        Cita c = buscarPorId(id);
        if (c != null) {
            c.setEstadoCita("CANCELADA"); 
            repoCita.save(c);
        }
    }

    @Override
    public Cita buscarPorId(int id) {
        return repoCita.findById(id).orElse(null);
    }
}