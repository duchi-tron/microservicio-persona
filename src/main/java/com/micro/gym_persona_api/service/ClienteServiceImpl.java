package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.gym_persona_api.exception.ConflictException;
import com.micro.gym_persona_api.model.Cliente;
import com.micro.gym_persona_api.model.Persona;
import com.micro.gym_persona_api.repository.ClienteRepository;
import com.micro.gym_persona_api.repository.PersonaRepository;

import jakarta.validation.Valid;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;
    private final PersonaRepository personaRepository;

    public ClienteServiceImpl(ClienteRepository repository, PersonaRepository personaRepository) {
        this.repository = repository;
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional
    public Cliente guardar(@Valid Cliente cliente) {
        validarCliente(cliente);
        vincularPersona(cliente);
        return repository.save(cliente);    
    }

    @Override
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long perId) {
        return repository.findById(perId);
    }

    @Override
    public Optional<Cliente> buscarPorCedula(String perCedula) {
        return repository.findByPersonaPerCedula(perCedula);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getPersona() == null) {
            throw new IllegalArgumentException("La persona es obligatoria para el cliente");
        }
        Persona persona = cliente.getPersona();

        if (persona.getPerCedula() == null || persona.getPerCedula().isBlank()) {
            throw new IllegalArgumentException("La cédula es obligatoria");
        }
        if (persona.getPerNombres() == null || persona.getPerNombres().isBlank()) {
            throw new IllegalArgumentException("Los nombres son obligatorios");
        }
        if (persona.getPerApellidos() == null || persona.getPerApellidos().isBlank()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios");
        }
        if (cliente.getPerPeso() == null || cliente.getPerPeso() <= 0) {
            throw new IllegalArgumentException("El peso debe ser un valor positivo");
        }
        if (cliente.getPerAltura() == null || cliente.getPerAltura() <= 0) {
            throw new IllegalArgumentException("La altura debe ser un valor positivo");
        }

        // Validar unicidad de per_cedula
        personaRepository.findByPerCedula(persona.getPerCedula())
                .ifPresent(p -> { throw new ConflictException("Ya existe un usuario registrado con esta cédula o teléfono"); });

        // Validar unicidad de per_telefono (si se proporciona)
        if (persona.getPerTelefono() != null && !persona.getPerTelefono().isBlank()) {
            personaRepository.findByPerTelefono(persona.getPerTelefono())
                    .ifPresent(p -> { throw new ConflictException("Ya existe un usuario registrado con esta cédula o teléfono"); });
        }
    }

    private void vincularPersona(Cliente cliente) {
    if (cliente.getPersona() != null) {    
        cliente.setPersona(cliente.getPersona());
    }
}
}