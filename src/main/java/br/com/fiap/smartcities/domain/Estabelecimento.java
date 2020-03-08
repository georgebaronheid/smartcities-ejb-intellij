package br.com.fiap.smartcities.domain;

import lombok.Data;
import lombok.Getter;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(schema = "RM83162", name = "tbl_estabelecimento")
@Data
@Getter
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_estabelecimento")
    private Integer id;

    @Column(name = "nome_estabelecimento", length = 50)
    private String nome;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dh_criacao")
    private Calendar dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_estabelecimento")
    private TipoEstabelecimento tipoEstabelecimento;

}
