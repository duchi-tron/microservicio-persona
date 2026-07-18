package com.micro.gym_persona_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_profesional", schema = "persona_db")
public class Profesional {

    @Id
    @Column(name = "med_usu_id", nullable = false, length = 100)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medUsuId;

    @Column(name = "per_id", nullable = false)
    private Long perId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_id", insertable = false, updatable = false)
    @JsonIgnore
    private Persona persona;

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

    public Long getMedUsuId() {
        return medUsuId;
    }

    public void setMedUsuId(Long medUsuId) {
        this.medUsuId = medUsuId;
    }

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
