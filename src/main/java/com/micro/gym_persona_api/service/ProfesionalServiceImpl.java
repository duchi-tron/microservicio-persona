package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.gym_persona_api.model.Profesional;
import com.micro.gym_persona_api.repository.PersonaRepository;
import com.micro.gym_persona_api.repository.ProfesionalRepository;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {
    private final ProfesionalRepository profesionalRepository;
    private final PersonaRepository personaRepository;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository, PersonaRepository personaRepository) {
        this.profesionalRepository = profesionalRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional
    public Profesional guardarProfesional(Profesional profesional) {
        validarProfesional(profesional);
        return profesionalRepository.save(profesional);
    }

    @Override
    public List<Profesional> listarProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public Optional<Profesional> buscarPorId(Long medUsuId) {
        return profesionalRepository.findById(medUsuId);
    }

    @Override
    public Optional<Profesional> buscarPorLicencia(String medNumeroLicencia) {
        return profesionalRepository.findByMedNumeroLicencia(medNumeroLicencia);
    }

    @Override
    public Optional<Profesional> buscarPorPerId(Long perId) {
        return profesionalRepository.findByPerId(perId);
    }

    @Override
    public List<Profesional> buscarPorEspecialidad(Profesional.Especialidad especialidad) {
        return profesionalRepository.findByMedEspecialidad(especialidad);
    }

    private void validarProfesional(Profesional profesional) {
        if (profesional.getPerId() == null) {
            throw new IllegalArgumentException("El perId es obligatorio para el profesional");
        }
        if (!personaRepository.existsById(profesional.getPerId())) {
            throw new IllegalArgumentException("No existe una persona con el ID: " + profesional.getPerId());
        }
        if (profesional.getMedNumeroLicencia() == null || profesional.getMedNumeroLicencia().isBlank()) {
            throw new IllegalArgumentException("El número de licencia es obligatorio");
        }
        if (profesional.getMedEspecialidad() == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }

        Optional<Profesional> existente = profesionalRepository.findByMedNumeroLicencia(profesional.getMedNumeroLicencia());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un profesional con esa licencia: " + profesional.getMedNumeroLicencia());
        }
    }

}
