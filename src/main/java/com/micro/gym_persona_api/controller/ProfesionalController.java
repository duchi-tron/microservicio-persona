package com.micro.gym_persona_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.gym_persona_api.model.Profesional;
import com.micro.gym_persona_api.service.ProfesionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    private final ProfesionalService service;

    public ProfesionalController(ProfesionalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Profesional profesional) {
        try {
            return ResponseEntity.status(201).body(service.guardarProfesional(profesional));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Profesional> listar() {
        return service.listarProfesionales();
    }

    @GetMapping("/especialidad")
    public List<Profesional> buscarPorEspecialidad(@RequestParam Profesional.Especialidad especialidad) {
        return service.buscarPorEspecialidad(especialidad);
    }
}
