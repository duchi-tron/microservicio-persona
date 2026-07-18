package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.gym_persona_api.model.Entrenador;
import com.micro.gym_persona_api.repository.EntrenadorRepository;

import jakarta.validation.Valid;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

public EntrenadorServiceImpl(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

   @Override
    @Transactional
    public Entrenador guardarEntrenador(@Valid Entrenador entrenador) {
        validarEntrenador(entrenador);
        
        if (entrenador.getPersona() != null) {
            entrenador.getPersona().setUsuId(null);
        }
        
        vincularPersona(entrenador);
        
        return entrenadorRepository.save(entrenador);
    }

    @Override
    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }

    @Override
    public Optional<Entrenador> buscarPorId(Long entId) {
        return entrenadorRepository.findById(entId);
    }

    @Override
    public List<Entrenador> buscarPorEspecialidad(Entrenador.Especialidad entEspecialidad) {
        return entrenadorRepository.findByEntEspecialidad(entEspecialidad);
    }

    @Override
    @Transactional
    public void eliminarEntrenador(Long perId) {
        if (!entrenadorRepository.existsById(perId)) {
            throw new RuntimeException("No se encontro entrenador con el ID " + perId + " para eliminar");
        }
        entrenadorRepository.deleteById(perId);
    }

    private void validarEntrenador(Entrenador entrenador) {
        if (entrenador.getPersona() == null) {
            throw new IllegalArgumentException("La persona es obligatoria para el entrenador");
        }
        if (entrenador.getPersona().getPerCedula() == null || entrenador.getPersona().getPerCedula().isBlank()) {
            throw new IllegalArgumentException("La cédula es obligatoria");
        }
        if (entrenador.getPersona().getPerNombres() == null || entrenador.getPersona().getPerNombres().isBlank()) {
            throw new IllegalArgumentException("Los nombres son obligatorios");
        }
        if (entrenador.getPersona().getPerApellidos() == null || entrenador.getPersona().getPerApellidos().isBlank()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        }
        if (entrenador.getEntEspecialidad() == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (entrenador.getEntProfesion() == null || entrenador.getEntProfesion().isBlank()) {
            throw new IllegalArgumentException("La profesión es obligatoria");
        }
    }

    private void vincularPersona(Entrenador entrenador) {
        if (entrenador.getPersona() != null) {
            entrenador.setPersona(entrenador.getPersona());
        }
    }

}