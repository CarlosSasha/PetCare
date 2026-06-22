package com.pet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.entity.Mascota;
import com.pet.repository.MascotaRepository;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository repoMascota;

    @Override
    public List<Mascota> listar() {
        return repoMascota.findAll();
    }

    @Override
    public void registrar(Mascota mascota) {
        repoMascota.save(mascota);
    }

    @Override
    public void actualizar(Mascota mascota) {
        repoMascota.save(mascota);
    }

    @Override
    public void desactivar(int id) {
        Mascota m = buscarPorId(id);
        if (m != null) {
            m.setEstado("INACTIVO");
            repoMascota.save(m);
        }
    }

    @Override
    public Mascota buscarPorId(int id) {
        return repoMascota.findById(id).orElse(null);
    }
}
