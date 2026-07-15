package com.micro.gym_persona_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ClienteRequestDTO {

    @NotBlank(message = "La cédula de la persona es obligatoria")
    @Size(max = 10, message = "La cédula no puede exceder 10 caracteres")
    private String perCedula;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser un valor positivo")
    private BigDecimal perPeso;

    @NotNull(message = "La altura es obligatoria")
    @Positive(message = "La altura debe ser un valor positivo")
    private BigDecimal perAltura;

    @NotBlank(message = "El objetivo es obligatorio")
    @Size(max = 200, message = "El objetivo no puede exceder 200 caracteres")
    private String perObjetivo;

    @Size(max = 500, message = "Las enfermedades no pueden exceder 500 caracteres")
    private String perEnfermedades;

    @Size(max = 500, message = "Las lesiones no pueden exceder 500 caracteres")
    private String perLesiones;

    @Size(max = 100, message = "La frecuencia deportiva no puede exceder 100 caracteres")
    private String perFreDeportiva;

    @Size(max = 500, message = "Los hábitos no pueden exceder 500 caracteres")
    private String perHabitos;

    public ClienteRequestDTO() {
    }

    public String getPerCedula() {
        return perCedula;
    }

    public void setPerCedula(String perCedula) {
        this.perCedula = perCedula;
    }

    public BigDecimal getPerPeso() {
        return perPeso;
    }

    public void setPerPeso(BigDecimal perPeso) {
        this.perPeso = perPeso;
    }

    public BigDecimal getPerAltura() {
        return perAltura;
    }

    public void setPerAltura(BigDecimal perAltura) {
        this.perAltura = perAltura;
    }

    public String getPerObjetivo() {
        return perObjetivo;
    }

    public void setPerObjetivo(String perObjetivo) {
        this.perObjetivo = perObjetivo;
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
}