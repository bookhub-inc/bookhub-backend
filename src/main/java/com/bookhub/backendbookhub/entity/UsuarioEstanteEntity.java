package com.bookhub.backendbookhub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario_estante")
public class UsuarioEstanteEntity {


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_livro")
    private Integer idLivro;

    @Column
    private Boolean lido;

    @Column
    private Boolean comprado;

    @Column
    private Boolean nota;

}
