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
@Table(name = "TOPICO_COMENTARIO")
@Entity
public class TopicoComentarioEntity {

    @Id
    private Integer id;

    @Column
    private String comentario;

    @Column(name = "dta_comentario")
    private LocalDateTime dataComentario;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_topico")
    private Integer idTopico;



}
