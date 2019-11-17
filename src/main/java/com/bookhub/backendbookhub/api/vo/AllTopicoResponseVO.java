package com.bookhub.backendbookhub.api.vo;

import com.bookhub.backendbookhub.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllTopicoResponseVO {

    private Integer id;
    private String titulo;
    private String descricao;
    @JsonDeserialize(using = LocalDateTimeConverter.Deserializer.class)
    @JsonSerialize(using = LocalDateTimeConverter.Serializer.class)
    private LocalDateTime dataCriacao;
    private String login;



}
