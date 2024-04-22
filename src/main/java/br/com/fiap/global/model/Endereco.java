package br.com.fiap.global.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ENDERECO")
    @SequenceGenerator(name = "SQ_ENDERECO", sequenceName = "SQ_ENDERECO", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ENDERECO")
    private Long id_endereco;

    @Column(name = "RUA_ENDERECO")
    private String rua_endereco;

    @Column(name = "CID_ENDERECO")
    private String cidade_endereco;

    @Column(name = "UF_ENDERECO")
    private String uf_endereco;

    @Column(name = "CEP_ENDERECO")
    private String cep_endereco;

    @Column(name = "NUM_ENDERECO")
    private String numero_endereco;


}
