package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.gym_persona_api.client.UsuarioClient;
import com.micro.gym_persona_api.exception.ConflictException;
import com.micro.gym_persona_api.model.Persona;
import com.micro.gym_persona_api.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;
    private final UsuarioClient usuarioClient;

   @Override
@Transactional
public Persona registrarPersona(Persona persona) {
    // 1. Validar existencia en el microservicio de Usuarios (El "Puente")
    // Esto garantiza que el usuId que recibes realmente exista en el otro micro
    try {
        var usuario = usuarioClient.obtenerUsuario(persona.getUsuId());
        if (usuario == null) {
            throw new ConflictException("No se encontró un usuario registrado con ID: " + persona.getUsuId());
        }
    } catch (Exception e) {
        throw new ConflictException("Error al conectar con el servicio de usuarios: " + e.getMessage());
    }

    // 2. Tus validaciones actuales se mantienen intactas
    if (personaRepository.existsByPerCedula(persona.getPerCedula())) {
        throw new ConflictException("Ya existe una persona con la cédula: " + persona.getPerCedula());
    }
    
    if (personaRepository.existsByUsuId(persona.getUsuId())) {
        throw new ConflictException("Ya existe una persona asociada al usuario con ID: " + persona.getUsuId());
    }

    // 3. Guardado final
    return personaRepository.save(persona);
}

    @Override
    public Optional<Persona> buscarPorUsuId(Long usuId) {
        return personaRepository.findByUsuId(usuId);
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }
}