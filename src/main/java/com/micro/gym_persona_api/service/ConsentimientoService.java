package com.micro.gym_persona_api.service;

import com.micro.gym_persona_api.model.Consentimiento;
import com.micro.gym_persona_api.model.Persona;
import com.micro.gym_persona_api.repository.ConsentimientoRepository;
import com.micro.gym_persona_api.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsentimientoService {

    private final ConsentimientoRepository consentimientoRepository;
    private final PersonaRepository personaRepository;

    public ConsentimientoService(ConsentimientoRepository consentimientoRepository,
                                 PersonaRepository personaRepository) {
        this.consentimientoRepository = consentimientoRepository;
        this.personaRepository = personaRepository;
    }

    @Transactional(readOnly = true)
    public List<Consentimiento> findAll() {
        return consentimientoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Consentimiento> findById(Long id) {
        return consentimientoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Consentimiento> findByVersionDocumento(String versionDocumento) {
        return Optional.ofNullable(consentimientoRepository.findByConVersionDocumento(versionDocumento));
    }

    @Transactional(readOnly = true)
    public List<Consentimiento> findByPerId(Long perId) {
        return consentimientoRepository.findByPersonaPerId(perId);
    }

    public Consentimiento save(Consentimiento consentimiento) {
        if (consentimiento.getConVersionDocumento() == null || consentimiento.getConVersionDocumento().isBlank()) {
            throw new IllegalArgumentException("La versión del documento es obligatoria");
        }

        if (consentimiento.getPersona() == null || consentimiento.getPersona().getPerId() == null) {
            throw new IllegalArgumentException("La persona es obligatoria");
        }

        if (consentimientoRepository.existsByConVersionDocumento(consentimiento.getConVersionDocumento())) {
            throw new IllegalStateException("Ya existe un consentimiento con la versión: " + consentimiento.getConVersionDocumento());
        }

        Persona persona = personaRepository.findById(consentimiento.getPersona().getPerId())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada: " + consentimiento.getPersona().getPerId()));

        consentimiento.setPersona(persona);
        return consentimientoRepository.save(consentimiento);
    }

    public void deleteById(Long id) {
        consentimientoRepository.deleteById(id);
    }

    public boolean existsByVersionDocumento(String versionDocumento) {
        return consentimientoRepository.existsByConVersionDocumento(versionDocumento);
    }
}