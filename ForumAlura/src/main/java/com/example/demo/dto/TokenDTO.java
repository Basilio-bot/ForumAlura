package com.example.demo.dto;

public class TokenDTO {
    private String token;
    private String tipo;

    public TokenDTO(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    // 🔥 GETTERS OBRIGATÓRIOS!
    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}