package com.micro.gym_persona_api.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long usuId;       
    private String usoNickName; 
    private Boolean usuEstado;
}
