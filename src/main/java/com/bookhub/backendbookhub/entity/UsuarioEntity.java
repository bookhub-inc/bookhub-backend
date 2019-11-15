package com.bookhub.backendbookhub.entity;

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

 @Column(name="dta_criacao",nullable = false)
 private LocalDateTime dataCriacao;

 @Column(name = "dta_ultacesso",nullable = false)
 private LocalDateTime dataUltimoAcesso;

 @Column(nullable = false)
 private String email;

 @Column(name = "id_avatar")
 private Integer idAvatar;

}
