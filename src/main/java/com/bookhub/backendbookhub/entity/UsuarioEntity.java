package com.bookhub.backendbookhub.entity;

import com.bookhub.backendbookhub.enums.Relacionamento;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@Table(name = "USUARIO")
@Entity
public class UsuarioEntity {

 @Id
 @GeneratedValue
 @ApiModelProperty(example="10")
 private Long id;

 @Column(nullable = false)
 @ApiModelProperty(example = "Arthur")
 private String nome;

 @Column
 @ApiModelProperty(example = "Rio")
 private String sobrenome;

 @Column
 private String facebook;

 @Column
 @ApiModelProperty(example = "11977451122")
 private String telefone;

 @Column
 @ApiModelProperty(example = "SOLTEIRO",allowableValues = "SOLTEIRO, CASADO, VIUVO, DIVORCIADO")
 private Relacionamento relacionamento;

 @Column
 @ApiModelProperty(example = "thor@gmail.com")
 private String email;

 @Column
 @ApiModelProperty(example = "thor")
 private String usuario;

 @Column
 @ApiModelProperty(example = "pokpk13123")
 private String senha;

 @Column
 @ApiModelProperty(example = "27441707200")
 private String cpf;

 public UsuarioEntity() {
 }

 public UsuarioEntity(final Long id, final String nome, final String sobrenome, final String facebook, final String telefone, final Relacionamento relacionamento, final String email, final String usuario, final String senha, final String cpf) {
  this.id = id;
  this.nome = nome;
  this.sobrenome = sobrenome;
  this.facebook = facebook;
  this.telefone = telefone;
  this.relacionamento = relacionamento;
  this.email = email;
  this.usuario = usuario;
  this.senha = senha;
  this.cpf = cpf;
 }

 public Long getId() {
  return id;
 }

 public void setId(final Long id) {
  this.id = id;
 }

 public String getNome() {
  return nome;
 }

 public void setNome(final String nome) {
  this.nome = nome;
 }

 public String getSobrenome() {
  return sobrenome;
 }

 public void setSobrenome(final String sobrenome) {
  this.sobrenome = sobrenome;
 }

 public String getFacebook() {
  return facebook;
 }

 public void setFacebook(final String facebook) {
  this.facebook = facebook;
 }

 public String getTelefone() {
  return telefone;
 }

 public void setTelefone(final String telefone) {
  this.telefone = telefone;
 }

 public Relacionamento getRelacionamento() {
  return relacionamento;
 }

 public void setRelacionamento(final Relacionamento relacionamento) {
  this.relacionamento = relacionamento;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(final String email) {
  this.email = email;
 }

 public String getUsuario() {
  return usuario;
 }

 public void setUsuario(final String usuario) {
  this.usuario = usuario;
 }

 public String getSenha() {
  return senha;
 }

 public void setSenha(final String senha) {
  this.senha = senha;
 }

 public String getCpf() {
  return cpf;
 }

 public void setCpf(final String cpf) {
  this.cpf = cpf;
 }
}
