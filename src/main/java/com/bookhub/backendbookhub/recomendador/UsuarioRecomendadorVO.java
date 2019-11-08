package com.bookhub.backendbookhub.recomendador;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRecomendadorVO {

    private Integer idUsuario;
    private Integer idLivro;
    private Integer nota;

}
