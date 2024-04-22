package br.com.fiap.global.model.dto;

public record Token(
    String token,
    String type,
    String prefix
    
 ) {}
