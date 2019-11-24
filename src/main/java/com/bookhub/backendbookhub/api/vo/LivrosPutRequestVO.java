package com.bookhub.backendbookhub.api.vo;


import com.bookhub.backendbookhub.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivrosPutRequestVO {

    private Integer id;
    @ApiModelProperty(example = "Harry Potter")
    private String nome;
    @ApiModelProperty(example = "Arthur Levine Books")
    private String editora;
    @ApiModelProperty(example = "J. K. Rowling")
    private String autor;
    @ApiModelProperty(example = "http://statics.livrariacultura.net.br/products/capas_lg/624/46583624.jpg")
    private String url;
    @ApiModelProperty(example = "1203")
    private Long nPaginas;
    private Boolean aprovado;
    private LocalDate dataLancamento;
    private String motivo;
    @JsonDeserialize(using = LocalDateTimeConverter.Deserializer.class)
    @JsonSerialize(using = LocalDateTimeConverter.Serializer.class)
    private LocalDateTime dataAtualizacao;
}
