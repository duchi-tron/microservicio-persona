package com.micro.gym_persona_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.micro.gym_persona_api.model.Persona;
import com.micro.gym_persona_api.repository.PersonaRepository;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaRepository repository;

    public PersonaController(PersonaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Persona> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Persona findById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada con id: " + id));
    }
}
