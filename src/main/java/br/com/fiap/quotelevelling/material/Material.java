package br.com.fiap.quotelevelling.material;

import br.com.fiap.quotelevelling.empresa.fornecedor.Fornecedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_MATERIAL")
public class Material {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_material;

    @Column(name = "NM_MATERIAL")
    private String nome_material;

    @Column(name = "QTD_MATERIAL")
    private int qtd_material;

    @Column(name = "VALUNIT_MATERIAL")
    private double ValUnit_material;

    @Column(name = "DESC_MATERIAL")
    private String descricao_material;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FORNECEDOR",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_MATERIAL_FORNECEDOR")
    )
    private Fornecedor fornecedor;

}