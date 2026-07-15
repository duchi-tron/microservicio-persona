package com.micro.gym_persona_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_entrenador", schema = "persona_db")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ent_id")
    private Long entId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "per_cedula", referencedColumnName = "per_cedula", nullable = false)
    @JsonManagedReference("entrenador")
    private Persona persona;

    @Column(name = "ent_especialidad", nullable = false, length = 100)
    @NotBlank(message = "La especialidad es obligatoria")
    private String entEspecialidad;

    @Column(name = "ent_profesion", nullable = false, length = 100)
    @NotBlank(message = "La profesión es obligatoria")
    private String entProfesion;

    @Column(name = "ent_experiencia", length = 500)
    private String entExperiencia;

    public Entrenador() {
    }

    public Entrenador(Long entId, Persona persona, String entEspecialidad, String entProfesion, String entExperiencia) {
        this.entId = entId;
        this.persona = persona;
        this.entEspecialidad = entEspecialidad;
        this.entProfesion = entProfesion;
        this.entExperiencia = entExperiencia;
    }

    public Long getEntId() {
        return entId;
    }

    public void setEntId(Long entId) {
        this.entId = entId;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getEntEspecialidad() {
        return entEspecialidad;
    }

    public void setEntEspecialidad(String entEspecialidad) {
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