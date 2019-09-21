package com.bookhub.backendbookhub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@Table(name = "CATEGORIA")
@Entity
public class CategoriaEntity {

 @Id
 private Long id;

 @Column(name = "nome")
 private String nome;

 public CategoriaEntity(final Long id, final String nome) {
  this.id = id;
  this.nome = nome;
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
}
