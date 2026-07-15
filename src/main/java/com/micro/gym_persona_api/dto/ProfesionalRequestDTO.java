package com.micro.gym_persona_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProfesionalRequestDTO {

    @NotBlank(message = "El número de licencia es obligatorio")
    @Size(max = 50, message = "El número de licencia no puede exceder 50 caracteres")
    private String medNumeroLicencia;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 100, message = "La especialidad no puede exceder 100 caracteres")
    private String medEspecialidad;

    @NotNull(message = "El ID de usuario médico es obligatorio")
    private String medUsuId;

    @NotBlank(message = "La cédula de la persona es obligatoria")
    @Size(max = 20, message = "La cédula no puede exceder 20 caracteres")
    private String perCedula;

    public ProfesionalRequestDTO() {
    }

    public String getMedNumeroLicencia() {
        return medNumeroLicencia;
    }

    public void setMedNumeroLicencia(String medNumeroLicencia) {
        this.medNumeroLicencia = medNumeroLicencia;
    }

    public String getMedEspecialidad() {
        return medEspecialidad;
    }

    public void setMedEspecialidad(String medEspecialidad) {
        this.medEspecialidad = medEspecialidad;
    }

    public String getMedUsuId() {
        return medUsuId;
    }

    public void setMedUsuId(String medUsuId) {
        this.medUsuId = medUsuId;
    }

    public String getPerCedula() {
        return perCedula;
    }

    public void setPerCedula(String perCedula) {
        this.perCedula = perCedula;
    }
}