package com.micro.gym_persona_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ConsentimientoRequestDTO {

    @NotBlank(message = "La versión del documento es obligatoria")
    @Size(max = 100, message = "La versión del documento no puede exceder 100 caracteres")
    private String conVersionDocumento;

    @NotNull(message = "El ID de persona es obligatorio")
    private Long perId;

    public ConsentimientoRequestDTO() {
    }

    public ConsentimientoRequestDTO(String conVersionDocumento, Long perId) {
        this.conVersionDocumento = conVersionDocumento;
        this.perId = perId;
    }

    public String getConVersionDocumento() {
        return conVersionDocumento;
    }

    public void setConVersionDocumento(String conVersionDocumento) {
        this.conVersionDocumento = conVersionDocumento;
    }

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }
}
