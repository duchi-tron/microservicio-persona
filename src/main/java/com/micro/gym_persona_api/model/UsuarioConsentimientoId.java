package com.micro.gym_persona_api.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioConsentimientoId implements Serializable {

    private Long usuId;
    private Long conId;

    public UsuarioConsentimientoId() {
    }

    public UsuarioConsentimientoId(Long usuId, Long conId) {
        this.usuId = usuId;
        this.conId = conId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioConsentimientoId that = (UsuarioConsentimientoId) o;
        return Objects.equals(usuId, that.usuId) && Objects.equals(conId, that.conId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuId, conId);
    }
}