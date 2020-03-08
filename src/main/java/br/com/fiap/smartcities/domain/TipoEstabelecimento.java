package br.com.fiap.smartcities.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(schema = "RM83162", name = "tbl_tipo_estabelecimento")
@Data
public class TipoEstabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tipo_estabelecimento")
    private Integer id;

    @Column(name = "nome_tipo_estabelecimento", length = 25, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "tipoEstabelecimento")
    private Collection<Estabelecimento> estabelecimentoCollection;
}
