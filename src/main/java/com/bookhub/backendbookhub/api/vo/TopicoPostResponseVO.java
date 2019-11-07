package com.bookhub.backendbookhub.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicoPostResponseVO {

    private Integer id;
    private String titulo;
    private Integer idUsuario;
    private Boolean spoiler;


}
