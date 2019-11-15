package com.bookhub.backendbookhub.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoComentarioPutRequestVO {

    private Integer id;
    private String comentario;


}
