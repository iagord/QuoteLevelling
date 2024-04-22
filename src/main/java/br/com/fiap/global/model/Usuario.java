package br.com.fiap.global.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.fiap.global.model.Empresas.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RESPONSAVEL")
    @SequenceGenerator(name = "SQ_RESPONSAVEL", sequenceName = "SQ_RESPONSAVEL", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NM_USUARIO")
    private String nome;

    @NotBlank
    @Email
    @Column(name = "EM_USUARIO")
    private String email;

    @Size(min = 8)
    @Column(name = "SE_USUARIO")
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "CLIENTE",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_USUARIO_CLIENTE")
    )
    private Cliente cliente;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }


    /* Regras de negócio obrigatótias de serem implementadas, mas não convém ao código*/

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, null, getAuthorities());
    }

}
