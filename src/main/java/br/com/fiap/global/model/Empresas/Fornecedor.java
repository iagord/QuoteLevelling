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
@Table(name = "TB_FORNECEDOR", uniqueConstraints = {
        @UniqueConstraint(name = "UK_FOR_CNPJ", columnNames = "CNPJ_FORNECEDOR")
})
@DiscriminatorValue("FORNECEDOR")
public class Fornecedor extends Empresa {

    @Column(name = "CNPJ_FORNECEDOR", nullable = false)
    @NotBlank
    @Size(min = 14 ,  max = 14, message = "Um CNPJ deve conter 14 digítos")
    private String cnpj_fornecedor;

    @Column(name = "AVALIACAO_FORNECEDOR")
    private double avaliacao_fornecedor;

}
