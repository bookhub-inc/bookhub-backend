package com.bookhub.backendbookhub.api.vo;

import com.bookhub.backendbookhub.entity.TopicoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoPostRequestVO {


    @ApiModelProperty(example = "Meu titulo teste")
    private String titulo;
    @ApiModelProperty(example = "Minha descricao teste")
    private String descricao;
    @ApiModelProperty(example = "1")
    private Integer idUsuario;


    public TopicoEntity toTopicoEntity(){
        return TopicoEntity.builder()
                .dataCriacao(LocalDateTime.now())
                .descricao(descricao)
                .idUsuario(idUsuario)
                .titulo(titulo)
                .spoiler(false)
                .build();
    }


}
