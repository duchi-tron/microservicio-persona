package com.micro.gym_persona_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tb_cliente", schema = "persona_db")
public class Cliente {

    @Id
    private Long perId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "per_id")
    @JsonIgnore
    private Persona persona;

    @Column(name = "per_peso")
    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser positivo")
    private Double perPeso;

    @Column(name = "per_altura")
    @NotNull(message = "La altura es obligatoria")
    @Positive(message = "La altura debe ser positiva")
    private Double perAltura;

    @Column(name = "per_enfermedades", length = 500)
    private String perEnfermedades;

    @Column(name = "per_lesiones", length = 500)
    private String perLesiones;

    @Column(name = "per_objetivo", length = 200)
    @NotBlank(message = "El objetivo es obligatorio")
    private String perObjetivo;

    @Column(name = "per_fre_deportiva", length = 100)
    private String perFreDeportiva;

    @Column(name = "per_habitos", length = 500)
    private String perHabitos;

    @Column(name = "rol_id")
    private Long rolId;

    public Cliente() {
    }

    public Cliente(Long perId, Persona persona, Double perPeso, Double perAltura, String perEnfermedades,
            String perLesiones, String perObjetivo, String perFreDeportiva, String perHabitos, Long rolId) {
        this.perId = perId;
        this.persona = persona;
        this.perPeso = perPeso;
        this.perAltura = perAltura;
        this.perEnfermedades = perEnfermedades;
        this.perLesiones = perLesiones;
        this.perObjetivo = perObjetivo;
        this.perFreDeportiva = perFreDeportiva;
        this.perHabitos = perHabitos;
        this.rolId = rolId;
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
        if (persona != null) {
            persona.setCliente(this);
        }
    }

    public Double getPerPeso() {
        return perPeso;
    }

    public void setPerPeso(Double perPeso) {
        this.perPeso = perPeso;
    }

    public Double getPerAltura() {
        return perAltura;
    }

    public void setPerAltura(Double perAltura) {
        this.perAltura = perAltura;
    }

    public String getPerEnfermedades() {
        return perEnfermedades;
    }

    public void setPerEnfermedades(String perEnfermedades) {
        this.perEnfermedades = perEnfermedades;
    }

    public String getPerLesiones() {
        return perLesiones;
    }

    public void setPerLesiones(String perLesiones) {
        this.perLesiones = perLesiones;
    }

    public String getPerObjetivo() {
        return perObjetivo;
    }

    public void setPerObjetivo(String perObjetivo) {
        this.perObjetivo = perObjetivo;
    }

    public String getPerFreDeportiva() {
        return perFreDeportiva;
    }

    public void setPerFreDeportiva(String perFreDeportiva) {
        this.perFreDeportiva = perFreDeportiva;
    }

    public String getPerHabitos() {
        return perHabitos;
    }

    public void setPerHabitos(String perHabitos) {
        this.perHabitos = perHabitos;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
}