package br.com.fiap.global.model;

import br.com.fiap.global.model.Empresas.Fornecedor;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MATERIAL")
    @SequenceGenerator(name = "SQ_MATERIAL", sequenceName = "SQ_MATERIAL", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_MATERIAL")
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
            name = "FORNECEDOR",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_MATERIAL_FORNECEDOR")
    )
    private Fornecedor fornecedor;

}
