package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.gym_persona_api.model.Entrenador;
import com.micro.gym_persona_api.repository.EntrenadorRepository;
import com.micro.gym_persona_api.repository.PersonaRepository;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final PersonaRepository personaRepository;

    public EntrenadorServiceImpl(EntrenadorRepository entrenadorRepository, PersonaRepository personaRepository) {
        this.entrenadorRepository = entrenadorRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional
    public Entrenador guardarEntrenador(Entrenador entrenador) {
        validarEntrenador(entrenador);
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
        if (entrenador.getPerId() == null) {
            throw new IllegalArgumentException("El perId es obligatorio para el entrenador");
        }
        if (!personaRepository.existsById(entrenador.getPerId())) {
            throw new IllegalArgumentException("No existe una persona con el ID: " + entrenador.getPerId());
        }
        if (entrenador.getEntEspecialidad() == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (entrenador.getEntProfesion() == null || entrenador.getEntProfesion().isBlank()) {
            throw new IllegalArgumentException("La profesión es obligatoria");
        }
    }

}
