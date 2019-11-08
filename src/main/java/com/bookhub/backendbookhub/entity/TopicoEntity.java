package com.bookhub.backendbookhub.entity;


import com.bookhub.backendbookhub.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TOPICO")
@Entity
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String titulo;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "dta_criacao")
    @JsonDeserialize(using = LocalDateTimeConverter.Deserializer.class)
    @JsonSerialize(using = LocalDateTimeConverter.Serializer.class)
    private LocalDateTime dataCriacao;

    @Column
    private String descricao;

    @Column
    private Boolean spoiler;

}
