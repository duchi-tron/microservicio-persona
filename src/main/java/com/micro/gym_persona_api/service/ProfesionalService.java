package com.micro.gym_persona_api.service;

import java.util.List;
import java.util.Optional;

import com.micro.gym_persona_api.model.Profesional;

public interface ProfesionalService {
    Profesional guardarProfesional(Profesional profesional);
    List<Profesional> listarProfesionales();
    Optional<Profesional> buscarPorId(Long medUsuId);
    Optional<Profesional> buscarPorLicencia(String medNumeroLicencia);
    Optional<Profesional> buscarPorPersonaCedula(String perCedula);
}