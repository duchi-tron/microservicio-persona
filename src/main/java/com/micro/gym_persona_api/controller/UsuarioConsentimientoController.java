package com.micro.gym_persona_api.controller;

import com.micro.gym_persona_api.model.UsuarioConsentimiento;
import com.micro.gym_persona_api.model.UsuarioConsentimientoId;
import com.micro.gym_persona_api.service.UsuarioConsentimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas/usuario-consentimientos")
public class UsuarioConsentimientoController {

    private final UsuarioConsentimientoService service;

    public UsuarioConsentimientoController(UsuarioConsentimientoService service) {
        this.service = service;
    }

    @PostMapping("/aceptar")
    public ResponseEntity<?> aceptarConsentimiento(@RequestParam Long usuId, @RequestParam Long conId) {
        try {
            return ResponseEntity.status(201).body(service.aceptarConsentimiento(usuId, conId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/aceptar/version")
    public ResponseEntity<?> aceptarConsentimientoPorVersion(@RequestParam Long usuId, @RequestParam String versionDocumento) {
        try {
            return ResponseEntity.status(201).body(service.aceptarConsentimientoPorVersion(usuId, versionDocumento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{usuId}")
    public List<UsuarioConsentimiento> listarPorUsuario(@PathVariable Long usuId) {
        return service.findByUsuId(usuId);
    }

    @GetMapping("/usuario/{usuId}/consentimiento/{conId}")
    public ResponseEntity<UsuarioConsentimiento> obtenerPorUsuarioYConsentimiento(@PathVariable Long usuId, @PathVariable Long conId) {
        return service.findByUsuIdAndConId(usuId, conId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuId}/verificar/{conId}")
    public ResponseEntity<Boolean> verificarAceptacion(@PathVariable Long usuId, @PathVariable Long conId) {
        return ResponseEntity.ok(service.haAceptado(usuId, conId));
    }

    @GetMapping("/usuario/{usuId}/verificar/version/{versionDocumento}")
    public ResponseEntity<Boolean> verificarAceptacionPorVersion(@PathVariable Long usuId, @PathVariable String versionDocumento) {
        return ResponseEntity.ok(service.haAceptadoVersion(usuId, versionDocumento));
    }

    @DeleteMapping("/usuario/{usuId}/consentimiento/{conId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long usuId, @PathVariable Long conId) {
        service.deleteById(new UsuarioConsentimientoId(usuId, conId));
        return ResponseEntity.noContent().build();
    }
}