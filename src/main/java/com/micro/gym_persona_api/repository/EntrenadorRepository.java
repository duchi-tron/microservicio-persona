package com.micro.gym_persona_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.gym_persona_api.model.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    List<Entrenador> findByEntEspecialidad(com.micro.gym_persona_api.model.Entrenador.Especialidad entEspecialidad);
}
