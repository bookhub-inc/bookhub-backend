package com.bookhub.backendbookhub.api.vo;

import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEstantePostRequestVO {

    @ApiModelProperty(example = "1")
    private Integer idUsuario;

    @ApiModelProperty(example = "1")
    private Integer idLivro;

    @ApiModelProperty(example = "true", allowableValues = "true or false")
    private Boolean lido;

    @ApiModelProperty(example = "true", allowableValues = "true or false")
    private Boolean comprado;

    @ApiModelProperty(example = "10", allowableValues = "range[1, 10]")
    private Integer nota;


    public UsuarioEstanteEntity toEntity(){
        return UsuarioEstanteEntity.builder()
                .idLivro(idLivro)
                .idUsuario(idUsuario)
                .lido(lido)
                .comprado(comprado)
                .nota(nota)
                .build();
    }


}
