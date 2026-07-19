package com.micro.gym_persona_api.controller;

import com.micro.gym_persona_api.model.UsuarioConsentimiento;
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

    @GetMapping("/buscar/por-persona")
    public List<UsuarioConsentimiento> buscarPorPerId(@RequestParam Long perId) {
        return service.findByPerId(perId);
    }

    @GetMapping("/buscar/por-consentimiento")
    public List<UsuarioConsentimiento> buscarPorConId(@RequestParam Long conId) {
        return service.findByConId(conId);
    }
}
