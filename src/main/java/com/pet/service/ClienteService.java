package com.pet.service;

import java.util.List;
import com.pet.entity.Cliente;

public interface ClienteService {
    List<Cliente> listar();
    void registrar(Cliente cliente);
    void actualizar(Cliente cliente);
    void desactivar(int id);
    Cliente buscarPorId(int id);
}