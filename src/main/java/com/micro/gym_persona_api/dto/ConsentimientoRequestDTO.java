package com.micro.gym_persona_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ConsentimientoRequestDTO {

    @NotBlank(message = "La versión del documento es obligatoria")
    @Size(max = 100, message = "La versión del documento no puede exceder 100 caracteres")
    private String conVersionDocumento;

    @Size(max = 255, message = "Los detalles no pueden exceder 255 caracteres")
    private String conDetalles;

    public ConsentimientoRequestDTO() {
    }

    public ConsentimientoRequestDTO(String conVersionDocumento, String conDetalles) {
        this.conVersionDocumento = conVersionDocumento;
        this.conDetalles = conDetalles;
    }

    public String getConVersionDocumento() {
        return conVersionDocumento;
    }

    public void setConVersionDocumento(String conVersionDocumento) {
        this.conVersionDocumento = conVersionDocumento;
    }

    public String getConDetalles() {
        return conDetalles;
    }

    public void setConDetalles(String conDetalles) {
        this.conDetalles = conDetalles;
    }
}
