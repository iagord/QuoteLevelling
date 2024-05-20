package br.com.fiap.quotelevelling.cotacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import br.com.fiap.quotelevelling.empresa.cliente.Cliente;
import br.com.fiap.quotelevelling.empresa.fornecedor.Fornecedor;
import br.com.fiap.quotelevelling.material.Material;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_COTACAO")
public class Cotacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
                            name = "ID_COTACAO",
                            referencedColumnName = "ID_COTACAO",
                            foreignKey = @ForeignKey(name = "FK_TB_MATERIAL_COTACAO")
                    )
            },
            inverseJoinColumns = {

                    @JoinColumn(
                            name = "ID_MATERIAL",
                            referencedColumnName = "ID_MATERIAL",
                            foreignKey = @ForeignKey(name = "FK_TB_COTACAO_MATERIAL")
                    )
            }
    )
    private Set<Material> materiais = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_COTACAO_CLIENTE")
    )
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FORNECEDOR",
            referencedColumnName = "ID_EMPRESA",
            foreignKey = @ForeignKey(name = "FK_COTACAO_FORNECEDOR")
    )
    private Fornecedor fornecedor;

    
}
