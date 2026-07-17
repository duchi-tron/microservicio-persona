package com.micro.gym_persona_api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.gym_persona_api.dto.UsuarioResponseDTO;

@FeignClient(name = "usuario-service", url = "http://localhost:8071") // Puerto del Usuario Service
public interface UsuarioClient {
    @GetMapping("/api/usuarios/{id}")
    UsuarioResponseDTO obtenerUsuario(@PathVariable("id") Long id);
}