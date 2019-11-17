package com.bookhub.backendbookhub.entity;

import com.bookhub.backendbookhub.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USUARIO")
@Entity
public class UsuarioEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;

 @Column(nullable = false)
 private String nome;

 @Column(nullable = false)
 private String sobrenome;

 @Column
 private String telefone;

 @Column
 private String login;

 @Column
 private String senha;

 @JsonDeserialize(using = LocalDateTimeConverter.Deserializer.class)
 @JsonSerialize(using = LocalDateTimeConverter.Serializer.class)
 @Column(name="dta_criacao",nullable = false)
 private LocalDateTime dataCriacao;

 @JsonDeserialize(using = LocalDateTimeConverter.Deserializer.class)
 @JsonSerialize(using = LocalDateTimeConverter.Serializer.class)
 @Column(name = "dta_ultacesso",nullable = false)
 private LocalDateTime dataUltimoAcesso;

 @Column(nullable = false)
 private String email;

 @Column(name = "id_avatar")
 private Integer idAvatar;

}
