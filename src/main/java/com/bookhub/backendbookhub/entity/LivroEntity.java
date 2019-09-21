package com.bookhub.backendbookhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@Table(name = "LIVRO")
@Entity
public class LivroEntity {

 @Id
 @GeneratedValue
 @Column(name = "ID_LIVRO")
 private Long id;

 @Column
 private String nome;

 @Column
 private Date dataLancamento;

 @Column
 private Long qtdadePaginas;

 public LivroEntity(final Long id, final String nome, final Date dataLancamento, final Long qtdadePaginas) {
  this.id = id;
  this.nome = nome;
  this.dataLancamento = dataLancamento;
  this.qtdadePaginas = qtdadePaginas;
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

 public Date getDataLancamento() {
  return dataLancamento;
 }

 public void setDataLancamento(final Date dataLancamento) {
  this.dataLancamento = dataLancamento;
 }

 public Long getQtdadePaginas() {
  return qtdadePaginas;
 }

 public void setQtdadePaginas(final Long qtdadePaginas) {
  this.qtdadePaginas = qtdadePaginas;
 }
}
