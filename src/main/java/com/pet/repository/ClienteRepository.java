package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
