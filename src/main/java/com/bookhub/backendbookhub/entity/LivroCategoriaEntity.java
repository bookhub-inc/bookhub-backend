package com.bookhub.backendbookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "livxcat")
public class LivroCategoriaEntity {

    @Column(name = "id_livro", nullable = false)
    private Integer idLivro;
    @Column(name = "id_cat", nullable = false)
    private Integer idCategoria;

}
