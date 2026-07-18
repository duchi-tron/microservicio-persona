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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_entrenador", schema = "persona_db")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ent_id")
    private Long entId;

    @Column(name = "per_id", nullable = false)
    private Long perId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_id", insertable = false, updatable = false)
    @JsonIgnore
    private Persona persona;

    @Column(name = "ent_especialidad", nullable = false, length = 100)
    @NotNull(message = "La especialidad es obligatoria")
    @Enumerated(EnumType.STRING)
    private Especialidad entEspecialidad;

    public enum Especialidad {
        CROSSFIT,
        YOGA,
        SPINNING,
        FUNCIONAL,
        BOXEO,
        PILATES
    }

    @Column(name = "ent_profesion", nullable = false, length = 100)
    @NotBlank(message = "La profesión es obligatoria")
    private String entProfesion;

    @Column(name = "ent_experiencia", length = 500)
    private String entExperiencia;

    public Entrenador() {
    }

    public Long getEntId() {
        return entId;
    }

    public void setEntId(Long entId) {
        this.entId = entId;
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

    public Especialidad getEntEspecialidad() {
        return entEspecialidad;
    }

    public void setEntEspecialidad(Especialidad entEspecialidad) {
        this.entEspecialidad = entEspecialidad;
    }

    public String getEntProfesion() {
        return entProfesion;
    }

    public void setEntProfesion(String entProfesion) {
        this.entProfesion = entProfesion;
    }

    public String getEntExperiencia() {
        return entExperiencia;
    }

    public void setEntExperiencia(String entExperiencia) {
        this.entExperiencia = entExperiencia;
    }
}
