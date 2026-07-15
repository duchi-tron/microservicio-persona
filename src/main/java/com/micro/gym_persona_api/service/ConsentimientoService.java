package com.micro.gym_persona_api.service;

import com.micro.gym_persona_api.model.Consentimiento;
import com.micro.gym_persona_api.repository.ConsentimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsentimientoService {

    private final ConsentimientoRepository consentimientoRepository;

    public ConsentimientoService(ConsentimientoRepository consentimientoRepository) {
        this.consentimientoRepository = consentimientoRepository;
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

    public Consentimiento save(Consentimiento consentimiento) {
        return consentimientoRepository.save(consentimiento);
    }

    public void deleteById(Long id) {
        consentimientoRepository.deleteById(id);
    }

    public boolean existsByVersionDocumento(String versionDocumento) {
        return consentimientoRepository.existsByConVersionDocumento(versionDocumento);
    }
}