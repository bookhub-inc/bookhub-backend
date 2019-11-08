package com.bookhub.backendbookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "livxcat")
public class LivroCategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "id_livro", nullable = false)
    private Integer idLivro;
    @Column(name = "id_cat", nullable = false)
    private Integer idCategoria;

}
