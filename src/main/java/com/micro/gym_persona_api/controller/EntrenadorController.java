package com.micro.gym_persona_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.micro.gym_persona_api.model.Entrenador;
import com.micro.gym_persona_api.service.EntrenadorService;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService service;

    public EntrenadorController(EntrenadorService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Entrenador> guardar(@RequestBody Entrenador entrenador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarEntrenador(entrenador));
    }

    @GetMapping
    public List<Entrenador> findAll() {
        return service.listarEntrenadores();
    }

    @GetMapping("/{perId}")
    public Entrenador findById(@PathVariable Long perId) {
        return service.buscarPorId(perId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador no encontrado con ID: " + perId));
    }

    @GetMapping("/especialidad")
    public List<Entrenador> buscarPorEspecialidad(@RequestParam Entrenador.Especialidad especialidad) {
        return service.buscarPorEspecialidad(especialidad);
    }

}
