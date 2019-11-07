package com.bookhub.backendbookhub.api.vo;

import com.bookhub.backendbookhub.entity.TopicoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoPostRequestVO {


    private String titulo;
    private String descricao;
    private Boolean spoiler;
    private Integer idUsuario;


    public TopicoEntity toTopicoEntity(){
        return TopicoEntity.builder()
                .dataCriacao(LocalDateTime.now())
                .descricao(descricao)
                .idUsuario(idUsuario)
                .titulo(titulo)
                .spoiler(spoiler)
                .build();
    }


}
