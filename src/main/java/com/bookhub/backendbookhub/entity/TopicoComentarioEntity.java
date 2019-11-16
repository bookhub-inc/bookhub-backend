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
@Table(name = "TOPICO_COMENTARIO")
@Entity
public class TopicoComentarioEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String comentario;

    @JsonDeserialize(using = LocalDateTimeConverter.Deserializer.class)
    @JsonSerialize(using = LocalDateTimeConverter.Serializer.class)
    @Column(name = "dta_comentario")
    private LocalDateTime dataComentario;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_topico")
    private Integer idTopico;



}
