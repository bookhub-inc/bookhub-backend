package com.bookhub.backendbookhub.api.vo;

import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;

public class UsuarioEstantePostRequestVO {

    private Integer idUsuario;

    private Integer idLivro;

    private Boolean lido;

    private Boolean comprado;

    private Boolean nota;


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
