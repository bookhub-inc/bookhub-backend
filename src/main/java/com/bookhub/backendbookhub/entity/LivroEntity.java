package com.bookhub.backendbookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "LIVRO")
@Entity
public class LivroEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column
 private Integer id;

 @Column(nullable = false)
 private String nome;

 @Column(nullable = false)
 private String autor;

 @Column
 private String editora;

 @Column
 private String descricao;

 @Column
 private Boolean aprovado;

 @Column(name = "dta_lancamento")
 private LocalDate dataLancamento;

 @Column(name = "n_paginas")
 private Long nPaginas;

 @Column(name = "url_livro")
 private String url;

}
