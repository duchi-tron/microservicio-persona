package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import com.micro.gym_persona_api.model.Entrenador;

public interface EntrenadorService {

    Entrenador guardarEntrenador(Entrenador entrenador);
    List<Entrenador> listarEntrenadores();
    Optional<Entrenador> buscarPorId(Long entId);
    List<Entrenador> buscarPorEspecialidad(String entEspecialidad);
    void eliminarEntrenador(Long entId);

}