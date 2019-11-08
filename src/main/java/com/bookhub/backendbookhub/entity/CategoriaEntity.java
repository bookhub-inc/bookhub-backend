package com.bookhub.backendbookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CATEGORIA")
@Entity
public class CategoriaEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column
 private Long id;

 @Column(name = "nome_categoria")
 private String nome;

}
