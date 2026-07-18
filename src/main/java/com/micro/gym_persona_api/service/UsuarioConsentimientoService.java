package com.micro.gym_persona_api.service;

import com.micro.gym_persona_api.model.Consentimiento;
import com.micro.gym_persona_api.model.UsuarioConsentimiento;
import com.micro.gym_persona_api.model.UsuarioConsentimientoId;
import com.micro.gym_persona_api.repository.ConsentimientoRepository;
import com.micro.gym_persona_api.repository.UsuarioConsentimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public List<UsuarioConsentimiento> findByUsuId(Long usuId) {
        return usuarioConsentimientoRepository.findById_UsuId(usuId);
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
        uc.setUsuConFechaAceptacion(LocalDate.now());

        return usuarioConsentimientoRepository.save(uc);
    }

    public UsuarioConsentimiento aceptarConsentimientoPorVersion(Long usuId, String versionDocumento) {
        Consentimiento consentimiento = consentimientoRepository.findByConVersionDocumento(versionDocumento);
        if (consentimiento == null) {
            throw new IllegalArgumentException("Consentimiento no encontrado para versión: " + versionDocumento);
        }
        return aceptarConsentimiento(usuId, consentimiento.getConId());
    }

    @Transactional(readOnly = true)
    public boolean haAceptado(Long usuId, Long conId) {
        return usuarioConsentimientoRepository.existsById(new UsuarioConsentimientoId(usuId, conId));
    }

    @Transactional(readOnly = true)
    public boolean haAceptadoVersion(Long usuId, String versionDocumento) {
        Consentimiento c = consentimientoRepository.findByConVersionDocumento(versionDocumento);
        if (c == null) return false;
        return usuarioConsentimientoRepository.existsById(new UsuarioConsentimientoId(usuId, c.getConId()));
    }

    public void deleteById(UsuarioConsentimientoId id) {
        usuarioConsentimientoRepository.deleteById(id);
    }
}