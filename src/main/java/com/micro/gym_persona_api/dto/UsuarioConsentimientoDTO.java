package com.micro.gym_persona_api.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class UsuarioConsentimientoDTO {

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuId;

    @NotNull(message = "El ID de consentimiento es obligatorio")
    private Long conId;

    @NotNull(message = "La fecha de aceptación es obligatoria")
    private LocalDate usuConFechaAceptacion;

    public UsuarioConsentimientoDTO() {
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public LocalDate getUsuConFechaAceptacion() {
        return usuConFechaAceptacion;
    }

    public void setUsuConFechaAceptacion(LocalDate usuConFechaAceptacion) {
        this.usuConFechaAceptacion = usuConFechaAceptacion;
    }
}