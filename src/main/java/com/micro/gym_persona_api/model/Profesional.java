package com.micro.gym_persona_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_profesional", schema = "persona_db")
public class Profesional {

    @Id
    @Column(name = "med_usu_id", nullable = false, length = 100)
    private String medUsuId;

    @OneToOne
    @JoinColumn(name = "per_cedula", referencedColumnName = "per_cedula", nullable = false)
    @JsonManagedReference("profesional")
    private Persona persona;

    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "med_numero_licencia", unique = true, nullable = false, length = 50)
    @NotBlank(message = "El número de licencia es obligatorio")
    private String medNumeroLicencia;

    @Column(name = "med_especialidad", nullable = false, length = 100)
    @NotBlank(message = "La especialidad es obligatoria")
    private String medEspecialidad;

    public Profesional() {
    }

    public Profesional(String medUsuId, Persona persona, Long rolId, String medNumeroLicencia, String medEspecialidad) {
        this.medUsuId = medUsuId;
        this.persona = persona;
        this.rolId = rolId;
        this.medNumeroLicencia = medNumeroLicencia;
        this.medEspecialidad = medEspecialidad;
    }

    public String getMedUsuId() {
        return medUsuId;
    }

    public void setMedUsuId(String medUsuId) {
        this.medUsuId = medUsuId;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
        if (persona != null) {
            persona.setProfesional(this);
        }
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
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
}