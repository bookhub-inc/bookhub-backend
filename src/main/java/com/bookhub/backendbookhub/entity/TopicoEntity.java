package com.bookhub.backendbookhub.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TOPICO")
@Entity
public class TopicoEntity {

    @Id
    private Integer id;

    @Column
    private String titulo;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "dta_criacao")
    private LocalDateTime dataCriacao;

    @Column
    private String descricao;

}
