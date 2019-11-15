package com.bookhub.backendbookhub.api.vo;

import com.bookhub.backendbookhub.entity.TopicoComentarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoComentarioPostRequestVO {

    private String comentario;

    private Integer idUsuario;

    private Integer idTopico;


    public TopicoComentarioEntity toEntity(){
        return TopicoComentarioEntity.builder()
                .comentario(comentario)
                .idTopico(idTopico)
                .idUsuario(idUsuario)
                .dataComentario(LocalDateTime.now())
                .build();
    }

}
