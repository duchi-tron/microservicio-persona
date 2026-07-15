package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import com.micro.gym_persona_api.model.Cliente;

public interface ClienteService {
    Cliente guardar (Cliente cliente);
    List<Cliente> listarClientes();
    Optional<Cliente> buscarPorId(Long perId);
    Optional<Cliente> buscarPorCedula(String perCedula);
}
