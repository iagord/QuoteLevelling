package br.com.fiap.global.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import br.com.fiap.global.model.Empresas.Cliente;
import br.com.fiap.global.model.Empresas.Fornecedor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_LEMBRETE")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COTACAO")
    @SequenceGenerator(name = "SQ_COTACAO", sequenceName = "SQ_COTACAO", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_COTACAO")
    private Long id_cotacao;

    @Column(name = "VALTOT_COTACAO")
    private double valTotal_cotacao;

    @Column(name = "QTDTOT_COTACAO")
    private int qtdTotal_cotacao;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "TB_COTACAO_MATERIAL",
            joinColumns = {
                    @JoinColumn(
                            name = "COTACAO",
                            referencedColumnName = "ID_COTACAO",
                            foreignKey = @ForeignKey(name = "FK_TB_MATERIAL_COTACAO")
                    )
            },
            inverseJoinColumns = {

                    @JoinColumn(
                            name = "MATERIAL",
                            referencedColumnName = "ID_MATERIAL",
                            foreignKey = @ForeignKey(name = "FK_TB_COTACAO_MATERIAL")
                    )
            }
    )
    private Set<Material> materiais = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "CLIENTE",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_COTACAO_CLIENTE")
    )
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "FORNECEDOR",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_COTACAO_FORNECEDOR")
    )
    private Fornecedor fornecedor;
}
