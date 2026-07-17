package com.micro.gym_persona_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.micro.gym_persona_api.dto.PersonaRequestDTO;
import com.micro.gym_persona_api.model.Persona;
import com.micro.gym_persona_api.service.PersonaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public List<Persona> findAll() {
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public Persona findById(@PathVariable Long id) {
        return personaService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada con id: " + id));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Persona> registrar(@RequestBody PersonaRequestDTO request) {
        Persona persona = new Persona();
        persona.setPerCedula(request.getPerCedula());
        persona.setUsuId(request.getUsuId());
        persona.setPerNombres(request.getPerNombres());
        persona.setPerApellidos(request.getPerApellidos());
        persona.setPerFechaNacimiento(request.getPerFechaNacimiento());
        persona.setPerGenero(request.getPerGenero());
        persona.setPerTelefono(request.getPerTelefono());
        persona.setPerDireccion(request.getPerDireccion());

        Persona registrada = personaService.registrarPersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrada);
    }

    @GetMapping("/usuario/{usuId}")
    public ResponseEntity<Persona> buscarPorUsuId(@PathVariable Long usuId) {
        Optional<Persona> persona = personaService.buscarPorUsuId(usuId);
        return persona.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}