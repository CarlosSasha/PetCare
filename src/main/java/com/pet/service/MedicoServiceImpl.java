package com.pet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.entity.Medico;
import com.pet.repository.MedicoRepository;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repoMedico;

    @Override
    public List<Medico> listar() {
        return repoMedico.findAll();
    }

    @Override
    public void registrar(Medico medico) {
        repoMedico.save(medico);
    }

    @Override
    public void actualizar(Medico medico) {
        repoMedico.save(medico);
    }

    @Override
    public void desactivar(int id) {
        Medico m = buscarPorId(id);
        if (m != null) {
            m.setEstado("INACTIVO");
            repoMedico.save(m);
        }
    }

    @Override
    public Medico buscarPorId(int id) {
        return repoMedico.findById(id).orElse(null);
    }
}