package com.micro.gym_persona_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.gym_persona_api.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByPerCedula(String perCedula);
    Optional<Persona> findByPerTelefono(String perTelefono);
    Optional<Persona> findByUsuId(Long usuId);
    boolean existsByPerCedula(String perCedula);
    boolean existsByUsuId(Long usuId);
}