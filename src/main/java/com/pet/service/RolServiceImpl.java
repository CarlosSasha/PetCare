package com.pet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.entity.Rol;
import com.pet.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository repoRol;

    @Override
    public List<Rol> listar() {
        return repoRol.findAll();
    }

    @Override
    public Rol buscarPorId(int id) {
        return repoRol.findById(id).orElse(null);
    }
}