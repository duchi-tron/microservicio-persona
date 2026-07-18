package com.micro.gym_persona_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Entity
@Table(name = "tb_consentimiento", schema = "persona_db")
public class Consentimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Long conId;

    @Column(name = "con_version_documento", length = 100, nullable = false, unique = true)
    private String conVersionDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_id", referencedColumnName = "per_id", nullable = false)
    @JsonBackReference("consentimiento-persona")
    private Persona persona;

    @OneToMany(mappedBy = "consentimiento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference("usuario-consentimiento-consentimiento")
    private List<UsuarioConsentimiento> usuarioConsentimientos;

    public Consentimiento() {
    }

    public Consentimiento(Long conId, String conVersionDocumento, Persona persona) {
        this.conId = conId;
        this.conVersionDocumento = conVersionDocumento;
        this.persona = persona;
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public String getConVersionDocumento() {
        return conVersionDocumento;
    }

    public void setConVersionDocumento(String conVersionDocumento) {
        this.conVersionDocumento = conVersionDocumento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Long getPerId() {
        return persona != null ? persona.getPerId() : null;
    }

    public List<UsuarioConsentimiento> getUsuarioConsentimientos() {
        return usuarioConsentimientos;
    }

    public void setUsuarioConsentimientos(List<UsuarioConsentimiento> usuarioConsentimientos) {
        this.usuarioConsentimientos = usuarioConsentimientos;
    }
}