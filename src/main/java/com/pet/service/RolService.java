package com.pet.service;

import java.util.List;
import com.pet.entity.Rol;

public interface RolService {
    List<Rol> listar();
    Rol buscarPorId(int id);
}