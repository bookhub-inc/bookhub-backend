package com.bookhub.backendbookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "LIVRO")
@Entity
public class LivroEntity {

 @Id
 @GeneratedValue
 @Column
 private Long id;

 @Column(nullable = false)
 private String nome;

 @Column(nullable = false)
 private String autor;

 @Column
 private String editora;

 @Column
 private String descricao;

 @Column
 private Integer aprovado;

 @Column(name = "dta_lancamento")
 private Date dataLancamento;

 @Column(name = "n_paginas")
 private Long nPaginas;

}
