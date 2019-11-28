package com.bookhub.backendbookhub.api.vo;

import com.bookhub.backendbookhub.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPutRequestVO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String telefone;

    private String email;
    private Integer idAvatar;
    @JsonDeserialize(using = LocalDateTimeConverter.Deserializer.class)
    @JsonSerialize(using = LocalDateTimeConverter.Serializer.class)
    private LocalDateTime dataUltimoAcesso;
    private String login;
    private String senha;

}
