package br.com.fiap.global.model.Empresas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CLIENTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CLI_CNPJ", columnNames = "CNPJ_CLIENTE")
})
@DiscriminatorValue("CLIENTE")
public class Cliente extends Empresa {

    @Column(name = "CNPJ_CLIENTE", nullable = false)
    @NotBlank
    @Size(min = 14 ,  max = 14, message = "Um CNPJ deve conter 14 digítos")
    private String cnpj_cliente;

}
