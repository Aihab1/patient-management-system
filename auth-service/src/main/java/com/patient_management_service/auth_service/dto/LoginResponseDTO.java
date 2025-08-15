package com.patient_management_service.auth_service.dto;

import lombok.Getter;

@Getter
public class LoginResponseDTO {
    private final String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}
