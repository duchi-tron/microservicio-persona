package com.micro.gym_persona_api.service;

import com.micro.gym_persona_api.model.Consentimiento;
import com.micro.gym_persona_api.model.UsuarioConsentimiento;
import com.micro.gym_persona_api.model.UsuarioConsentimientoId;
import com.micro.gym_persona_api.repository.ConsentimientoRepository;
import com.micro.gym_persona_api.repository.UsuarioConsentimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioConsentimientoService {

    private final UsuarioConsentimientoRepository usuarioConsentimientoRepository;
    private final ConsentimientoRepository consentimientoRepository;

    public UsuarioConsentimientoService(UsuarioConsentimientoRepository usuarioConsentimientoRepository,
                                        ConsentimientoRepository consentimientoRepository) {
        this.usuarioConsentimientoRepository = usuarioConsentimientoRepository;
        this.consentimientoRepository = consentimientoRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioConsentimiento> findByPerId(Long perId) {
        return usuarioConsentimientoRepository.findById_UsuId(perId);
    }

    @Transactional(readOnly = true)
    public List<UsuarioConsentimiento> findByConId(Long conId) {
        return usuarioConsentimientoRepository.findByConsentimientoConId(conId);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioConsentimiento> findByUsuIdAndConId(Long usuId, Long conId) {
        return usuarioConsentimientoRepository.findById(new UsuarioConsentimientoId(usuId, conId));
    }

    public UsuarioConsentimiento aceptarConsentimiento(Long usuId, Long conId) {
        UsuarioConsentimientoId id = new UsuarioConsentimientoId(usuId, conId);
        if (usuarioConsentimientoRepository.existsById(id)) {
            throw new IllegalStateException("El usuario ya ha aceptado este consentimiento");
        }

        Consentimiento consentimiento = consentimientoRepository.findById(conId)
                .orElseThrow(() -> new IllegalArgumentException("Consentimiento no encontrado: " + conId));

        UsuarioConsentimiento uc = new UsuarioConsentimiento();
        uc.setId(new UsuarioConsentimientoId(usuId, conId));
        uc.setConsentimiento(consentimiento);
        uc.setUsuConFechaAceptacion(java.time.LocalDate.now());

        return usuarioConsentimientoRepository.save(uc);
    }

    public void deleteById(UsuarioConsentimientoId id) {
        usuarioConsentimientoRepository.deleteById(id);
    }
}
