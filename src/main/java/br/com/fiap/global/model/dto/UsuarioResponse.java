package br.com.fiap.global.model.dto;

import br.com.fiap.global.model.Usuario;

public record UsuarioResponse(
    Long id,
    String nome,
    String email
) {

    public static UsuarioResponse fromUsuario(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
    
}
