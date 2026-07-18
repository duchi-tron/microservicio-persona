package com.micro.gym_persona_api.controller;

import com.micro.gym_persona_api.model.Consentimiento;
import com.micro.gym_persona_api.service.ConsentimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas/consentimientos")
public class ConsentimientoController {

    private final ConsentimientoService service;

    public ConsentimientoController(ConsentimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Consentimiento consentimiento) {
        try {
            return ResponseEntity.status(201).body(service.save(consentimiento));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Consentimiento> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consentimiento> obtenerPorId(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/version/{versionDocumento}")
    public ResponseEntity<Consentimiento> obtenerPorVersion(@PathVariable String versionDocumento) {
        return service.findByVersionDocumento(versionDocumento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}