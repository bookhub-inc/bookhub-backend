package com.bookhub.backendbookhub.api.vo;


import com.bookhub.backendbookhub.entity.LivroEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivrosPostRequestVO {

    @ApiModelProperty(example = "Harry Potter")
    private String nome;
    @ApiModelProperty(example = "Arthur Levine Books")
    private String editora;
    @ApiModelProperty(example = "J. K. Rowling")
    private String autor;
    @ApiModelProperty(example = "[1,2,3]")
    private List<Integer> categorias;
    @ApiModelProperty(example = "http://statics.livrariacultura.net.br/products/capas_lg/624/46583624.jpg")
    private String url;
    @ApiModelProperty(example = "1203")
    private Long nPaginas;

    public LivroEntity toEntity(){
        return LivroEntity.builder()
                .nome(nome)
                .editora(editora)
                .autor(autor)
                .url(url)
                .nPaginas(nPaginas)
                .dataAtualizacao(LocalDateTime.now())
                .aprovado(false)
                .build();
    }
}
