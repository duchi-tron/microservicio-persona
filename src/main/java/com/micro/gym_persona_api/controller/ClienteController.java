package com.micro.gym_persona_api.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.gym_persona_api.model.Cliente;
import com.micro.gym_persona_api.service.ClienteService;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(service.guardar(cliente));
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listarClientes();
    }
    
}
