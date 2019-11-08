package com.bookhub.backendbookhub.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostResponseVO {

    @ApiModelProperty(example="10")
    private Integer id;

    private String nome;

    private String telefone;

    private String email;

    private String usuario;

    private Integer idAvatar;

}
