package br.com.fiap.global.model;

import br.com.fiap.global.model.Empresas.Cliente;
import br.com.fiap.global.model.Empresas.Fornecedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_RESPONSAVELL")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RESPONSAVEL")
    @SequenceGenerator(name = "SQ_RESPONSAVEL", sequenceName = "SQ_RESPONSAVEL", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_RESPONSAVEL")
    private Long id_responsavel;

    @Column(name = "NM_RESPONSAVEL")
        private String nome_responsavel;

    @Column(name = "DESC_RESPONSAVEL")
    private String descricao_responsavel;

    @Column(name = "MATRI_RESPONSAVEL")
    private String matricula_responsavel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "FORNECEDOR",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_RESPONSAVEL_FORNECEDOR")
    )
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "CLIENTE",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_RESPONSAVEL_CLIENTE")
    )
    private Cliente cliente;

}
