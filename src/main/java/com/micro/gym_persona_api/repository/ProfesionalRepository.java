package com.micro.gym_persona_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.gym_persona_api.model.Profesional;


@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    Optional<Profesional> findByMedNumeroLicencia(String medNumeroLicencia);
    Optional<Profesional> findByPerId(Long perId);
    List<Profesional> findByMedEspecialidad(com.micro.gym_persona_api.model.Profesional.Especialidad medEspecialidad);
}