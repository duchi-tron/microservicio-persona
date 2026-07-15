package com.micro.gym_persona_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tb_usuario_consentimiento", schema = "persona_db")
public class UsuarioConsentimiento {

    @EmbeddedId
    private UsuarioConsentimientoId id;

    @ManyToOne
    @MapsId("conId")
    @JoinColumn(name = "con_id", nullable = false)
    private Consentimiento consentimiento;

    @ManyToOne
    @MapsId("usuId")
    @JoinColumn(name = "usu_id", referencedColumnName = "per_id", nullable = false)
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