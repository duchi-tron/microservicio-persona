package com.micro.gym_persona_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_persona", schema = "persona_db")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long perId;

    @Column(name = "per_cedula", unique = true, nullable = false)
    private String perCedula;

    @Column(name = "usu_id")
    private Long usuId;

    @Column(name = "per_nombres", nullable = false)
    private String perNombres;

    @Column(name = "per_apellidos", nullable = false)
    private String perApellidos;

    @Column(name = "per_fecha_nacimiento")
    private LocalDate perFechaNacimiento;

    @Column(name = "per_genero")
    private String perGenero;

    @Column(name = "per_telefono")
    private String perTelefono;

    @Column(name = "per_direccion")
    private String perDireccion;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference("entrenador")
    private Entrenador entrenador;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference("profesional")
    private Profesional profesional;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference("cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("usuario-consentimiento-persona")
    private List<UsuarioConsentimiento> usuarioConsentimientos;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("consentimiento-persona")
    private List<Consentimiento> consentimientos;

    public Persona() {
    }

    public Persona(Long perId, String perCedula, Long usuId, String perNombres, String perApellidos,
            LocalDate perFechaNacimiento, String perGenero, String perTelefono, String perDireccion) {
        this.perId = perId;
        this.perCedula = perCedula;
        this.usuId = usuId;
        this.perNombres = perNombres;
        this.perApellidos = perApellidos;
        this.perFechaNacimiento = perFechaNacimiento;
        this.perGenero = perGenero;
        this.perTelefono = perTelefono;
        this.perDireccion = perDireccion;
    }

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public String getPerCedula() {
        return perCedula;
    }

    public void setPerCedula(String perCedula) {
        this.perCedula = perCedula;
    }

    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getPerNombres() {
        return perNombres;
    }

    public void setPerNombres(String perNombres) {
        this.perNombres = perNombres;
    }

    public String getPerApellidos() {
        return perApellidos;
    }

    public void setPerApellidos(String perApellidos) {
        this.perApellidos = perApellidos;
    }

    public LocalDate getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(LocalDate perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    public String getPerGenero() {
        return perGenero;
    }

    public void setPerGenero(String perGenero) {
        this.perGenero = perGenero;
    }

    public String getPerTelefono() {
        return perTelefono;
    }

    public void setPerTelefono(String perTelefono) {
        this.perTelefono = perTelefono;
    }

    public String getPerDireccion() {
        return perDireccion;
    }

    public void setPerDireccion(String perDireccion) {
        this.perDireccion = perDireccion;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<UsuarioConsentimiento> getUsuarioConsentimientos() {
        return usuarioConsentimientos;
    }

    public void setUsuarioConsentimientos(List<UsuarioConsentimiento> usuarioConsentimientos) {
        this.usuarioConsentimientos = usuarioConsentimientos;
    }

    public List<Consentimiento> getConsentimientos() {
        return consentimientos;
    }

    public void setConsentimientos(List<Consentimiento> consentimientos) {
        this.consentimientos = consentimientos;
    }
}