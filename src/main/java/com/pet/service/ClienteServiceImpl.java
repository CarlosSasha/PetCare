package com.pet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pet.entity.Cliente;
import com.pet.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
    private ClienteRepository repoCliente;

    @Override
    public List<Cliente> listar() {
        return repoCliente.findAll();
    }

    @Override
    public void registrar(Cliente cliente) {
        repoCliente.save(cliente);
    }

    @Override
    public void actualizar(Cliente cliente) {
        repoCliente.save(cliente);
    }

    @Override
    public void desactivar(int id) {
        Cliente c = buscarPorId(id);
        if (c != null) {
            c.setEstado("INACTIVO");
            repoCliente.save(c);
        }
    }

    @Override
    public Cliente buscarPorId(int id) {
        return repoCliente.findById(id).orElse(null);
    }
}
