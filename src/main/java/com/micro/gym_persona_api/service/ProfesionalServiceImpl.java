package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.gym_persona_api.model.Profesional;
import com.micro.gym_persona_api.repository.ProfesionalRepository;

import jakarta.validation.Valid;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {
    private final ProfesionalRepository profesionalRepository;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }

    @Override
    @Transactional
    public Profesional guardarProfesional(@Valid Profesional profesional) {
        validarProfesional(profesional);
        vincularPersona(profesional);
        return profesionalRepository.save(profesional);
    }

    @Override
    public List<Profesional> listarProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public Optional<Profesional> buscarPorId(String medUsuId) {
        return profesionalRepository.findById(medUsuId);
    }

    @Override
    public Optional<Profesional> buscarPorLicencia(String medNumeroLicencia) {
        return profesionalRepository.findByMedNumeroLicencia(medNumeroLicencia);
    }

    @Override
    public Optional<Profesional> buscarPorPersonaCedula(String perCedula) {
        return profesionalRepository.findByPersonaPerCedula(perCedula);
    }

    private void validarProfesional(Profesional profesional) {
        if (profesional.getPersona() == null) {
            throw new IllegalArgumentException("La persona es obligatoria para el profesional");
        }
        if (profesional.getPersona().getPerCedula() == null || profesional.getPersona().getPerCedula().isBlank()) {
            throw new IllegalArgumentException("La cédula es obligatoria");
        }
        if (profesional.getPersona().getPerNombres() == null || profesional.getPersona().getPerNombres().isBlank()) {
            throw new IllegalArgumentException("Los nombres son obligatorios");
        }
        if (profesional.getPersona().getPerApellidos() == null || profesional.getPersona().getPerApellidos().isBlank()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        }
        if (profesional.getMedNumeroLicencia() == null || profesional.getMedNumeroLicencia().isBlank()) {
            throw new IllegalArgumentException("El número de licencia es obligatorio");
        }
        if (profesional.getMedEspecialidad() == null || profesional.getMedEspecialidad().isBlank()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }

        Optional<Profesional> existente = profesionalRepository.findByMedNumeroLicencia(profesional.getMedNumeroLicencia());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un profesional con esa licencia: " + profesional.getMedNumeroLicencia());
        }
    }

    private void vincularPersona(Profesional profesional) {
        if (profesional.getPersona() != null) {
            profesional.setPersona(profesional.getPersona());
        }
    }

}