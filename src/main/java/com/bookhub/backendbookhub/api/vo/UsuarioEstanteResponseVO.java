package com.bookhub.backendbookhub.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//select l.id,l.nome,l.autor,l.descricao,l.url_livro,l.n_paginas,ae.nota,ae.lido,ae.comprado,ae.id_usuario
//                inner join livro l on l.id = ae.id_livro
//                where ae.id_usuario = 2


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEstanteResponseVO {

    private Integer id;
    private String nome;
    private String autor;
    private String descricao;
    private String url;
    private Integer nPaginas;
    private Integer nota;
    private Boolean lido;
    private Boolean comprado;
    private Integer idUsuario;

}
