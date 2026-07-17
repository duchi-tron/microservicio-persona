package com.micro.gym_persona_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_profesional", schema = "persona_db")
public class Profesional {

    @Id
    @Column(name = "med_usu_id", nullable = false, length = 100)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medUsuId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "per_cedula", referencedColumnName = "per_cedula", nullable = false)
    @JsonIgnore
    private Persona persona;

    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "med_numero_licencia", unique = true, nullable = false, length = 50)
    @NotBlank(message = "El número de licencia es obligatorio")
    private String medNumeroLicencia;

    @Column(name = "med_especialidad", nullable = false, length = 100)
    @NotBlank(message = "La especialidad es obligatoria")
    @Enumerated(EnumType.STRING)
    private Especialidad medEspecialidad;

    public enum Especialidad {
        NUTRICIONISTA,
        ENTRENADOR_PERSONAL,
        FISIOTERAPEUTA,
        PSICOLOGO_DEPORTIVO,
        MEDICO_DEPORTIVO,
        CONSULTA_GENERAL
    }

    public Profesional() {
    }

    public Profesional(Long medUsuId, Persona persona, Long rolId, String medNumeroLicencia, String medEspecialidad) {
        this.medUsuId = medUsuId;
        this.persona = persona;
        this.rolId = rolId;
        this.medNumeroLicencia = medNumeroLicencia;
        this.medEspecialidad = Especialidad.valueOf(medEspecialidad);
    }

    public Long getMedUsuId() {
        return medUsuId;
    }

    public void setMedUsuId(Long medUsuId) {
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

    public Especialidad getMedEspecialidad() {
        return medEspecialidad;
    }

    public void setMedEspecialidad(Especialidad medEspecialidad) {
        this.medEspecialidad = medEspecialidad;
    }
}