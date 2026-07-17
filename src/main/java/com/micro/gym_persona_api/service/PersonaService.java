package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import com.micro.gym_persona_api.model.Persona;

public interface PersonaService {
    Persona registrarPersona(Persona persona);
    Optional<Persona> buscarPorUsuId(Long usuId);
    List<Persona> findAll();
    Optional<Persona> findById(Long id);
}