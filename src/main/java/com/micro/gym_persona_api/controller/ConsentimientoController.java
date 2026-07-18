package com.micro.gym_persona_api.controller;

import com.micro.gym_persona_api.dto.ConsentimientoRequestDTO;
import com.micro.gym_persona_api.model.Consentimiento;
import com.micro.gym_persona_api.service.ConsentimientoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas/consentimientos")
public class ConsentimientoController {

    private final ConsentimientoService service;

    public ConsentimientoController(ConsentimientoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody ConsentimientoRequestDTO request) {
        try {
            Consentimiento consentimiento = new Consentimiento();
            consentimiento.setConVersionDocumento(request.getConVersionDocumento());

            com.micro.gym_persona_api.model.Persona persona = new com.micro.gym_persona_api.model.Persona();
            persona.setPerId(request.getPerId());
            consentimiento.setPersona(persona);

            return ResponseEntity.status(201).body(service.save(consentimiento));
        } catch (IllegalArgumentException | IllegalStateException e) {
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

    @GetMapping("/persona/{perId}")
    public ResponseEntity<List<Consentimiento>> obtenerPorPersona(@PathVariable Long perId) {
        List<Consentimiento> consentimientos = service.findByPerId(perId);
        if (consentimientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(consentimientos);
    }
}