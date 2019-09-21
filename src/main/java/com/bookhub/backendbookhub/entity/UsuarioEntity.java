package com.bookhub.backendbookhub.entity;

import com.bookhub.backendbookhub.enums.Relacionamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@Table(name = "USUARIO")
@Entity
public class UsuarioEntity {

 @Id
 @GeneratedValue
 private Long id;
 @Column(nullable = false)
 private String nome;
 @Column
 private String sobrenome;
 @Column
 private String facebook;
 @Column
 private String telefone;
 @Column
 private Relacionamento relacionamento;
 @Column
 private String email;
 @Column
 private String usuario;
 @Column
 private String senha;
 @Column
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
