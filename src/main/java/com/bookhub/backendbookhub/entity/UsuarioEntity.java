package com.bookhub.backendbookhub.entity;

import com.bookhub.backendbookhub.enums.Relacionamento;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
 private String facebook;

 @Column
 private String telefone;

 @Column
 private Relacionamento relacionamento;

// @Column
// @ApiModelProperty(example = "thor@gmail.com")
// private String email;

 @Column
 private String usuario;

 @Column
 private String senha;

 @Column
 private String cpf;

 @Column(name="dta_criacao",nullable = false)
 private LocalDateTime dataCriacao;

 @Column(name = "dta_ultacesso",nullable = false)
 private LocalDateTime dataUltimoAcesso;

 @Column(nullable = false)
 private String email;

 @Column(name = "id_avatar")
 private Integer idAvatar;

}
