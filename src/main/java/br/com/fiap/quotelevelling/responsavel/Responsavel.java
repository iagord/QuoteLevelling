package br.com.fiap.quotelevelling.responsavel;


import br.com.fiap.quotelevelling.empresa.cliente.Cliente;
import br.com.fiap.quotelevelling.empresa.fornecedor.Fornecedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_RESPONSAVEL")
public class Responsavel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_responsavel;

    @Column(name = "NM_RESPONSAVEL")
        private String nome_responsavel;

    @Column(name = "DESC_RESPONSAVEL")
    private String descricao_responsavel;

    @Column(name = "MATRI_RESPONSAVEL")
    private String matricula_responsavel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FORNECEDOR",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_RESPONSAVEL_FORNECEDOR")
    )
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_RESPONSAVEL_CLIENTE")
    )
    private Cliente cliente;


}