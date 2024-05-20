package br.com.fiap.quotelevelling.empresa;

import java.io.Serializable;

import br.com.fiap.quotelevelling.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_EMPRESA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Empresa implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Long id_empresa;

    @Column(name = "NM_EMPRESA")
    private String nome_empresa;

    @Column(name = "DESC_EMPRESA")
        private String descricao_empresa;

    @Column(name = "TP_EMPRESA")
    private String tipo_empresa;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ID_ENDERECO",
            referencedColumnName = "ID_ENDERECO",
            foreignKey = @ForeignKey(name = "FK_EMPRESA_ENDERECO")
    )
    private Endereco endereco;
}
