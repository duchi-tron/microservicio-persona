package com.micro.gym_persona_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_usuario_consentimiento", schema = "persona_db")
public class UsuarioConsentimiento {

    @EmbeddedId
    private UsuarioConsentimientoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("conId")
    @JoinColumn(name = "con_id", nullable = false)
    @JsonManagedReference("usuario-consentimiento-consentimiento")
    private Consentimiento consentimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuId")
    @JoinColumn(name = "usu_id", referencedColumnName = "per_id", nullable = false)
    @JsonBackReference("usuario-consentimiento-persona")
    private Persona persona;

    @Column(name = "usu_con_fecha_aceptacion", nullable = false)
    private LocalDate usuConFechaAceptacion;

    public UsuarioConsentimiento() {
    }

    public UsuarioConsentimiento(UsuarioConsentimientoId id, Consentimiento consentimiento, Persona persona, LocalDate usuConFechaAceptacion) {
        this.id = id;
        this.consentimiento = consentimiento;
        this.persona = persona;
        this.usuConFechaAceptacion = usuConFechaAceptacion;
    }

    public UsuarioConsentimientoId getId() {
        return id;
    }

    public void setId(UsuarioConsentimientoId id) {
        this.id = id;
    }

    public Consentimiento getConsentimiento() {
        return consentimiento;
    }

    public void setConsentimiento(Consentimiento consentimiento) {
        this.consentimiento = consentimiento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Long getUsuId() {
        return id != null ? id.getUsuId() : null;
    }

    public Long getConId() {
        return id != null ? id.getConId() : null;
    }

    public LocalDate getUsuConFechaAceptacion() {
        return usuConFechaAceptacion;
    }

    public void setUsuConFechaAceptacion(LocalDate usuConFechaAceptacion) {
        this.usuConFechaAceptacion = usuConFechaAceptacion;
    }
}