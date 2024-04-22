package br.com.fiap.global.model.Empresas;

import java.io.Serializable;

import br.com.fiap.global.model.Endereco;
import br.com.fiap.global.validation.TipoEmpresa;
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
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EMPRESA")
    @SequenceGenerator(name = "SQ_EMPRESA", sequenceName = "SQ_EMPRESA", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_EMPRESA")
    private Long id_empresa;

    @Column(name = "NM_EMPRESA")
    private String nome_empresa;

    @Column(name = "DESC_EMPRESA")
        private String descricao_empresa;

    @TipoEmpresa
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
