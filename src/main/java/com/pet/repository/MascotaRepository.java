package com.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pet.entity.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Integer>{

}
