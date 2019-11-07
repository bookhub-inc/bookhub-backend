package com.bookhub.backendbookhub.api.vo;


import com.bookhub.backendbookhub.entity.UsuarioEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestVO {

    @ApiModelProperty(example = "Arthur")
    private String nome;

    @ApiModelProperty(example = "Rio")
    private String sobrenome;

    @ApiModelProperty(example = "11977451122")
    private String telefone;

    private String email;

    @ApiModelProperty(example = "thor")
    private String usuario;

    @ApiModelProperty(example = "pokpk13123")
    private String senha;

    @ApiModelProperty(example = "1")
    private Integer idAvatar;


    public UsuarioEntity toUsuarioEntity(){

        return UsuarioEntity.builder()
                .nome(nome)
                .sobrenome(sobrenome)
                .telefone(telefone)
                .email(email)
                .usuario(usuario)
                .senha(senha)
                .idAvatar(idAvatar==null ? 1 : idAvatar)
                .dataCriacao(LocalDateTime.now())
                .dataUltimoAcesso(LocalDateTime.now())
                .build();
    }


}
