package com.micro.gym_persona_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.gym_persona_api.model.Profesional;


@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, String> {
    Optional<Profesional> findByMedNumeroLicencia(String medNumeroLicencia);
    Optional<Profesional> findByPersonaPerCedula(String perCedula);
}