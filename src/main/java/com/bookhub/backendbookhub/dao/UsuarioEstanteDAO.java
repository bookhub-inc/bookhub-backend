package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.entity.LivroRecomendadoEntity;
import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;
import com.bookhub.backendbookhub.recomendador.UsuarioRecomendadorVO;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Repository
@Transactional
public class UsuarioEstanteDAO {

    @Autowired
    private EntityManager em;

    public List<UsuarioRecomendadorVO> findAllRecomendador() {


        List<UsuarioEstanteEntity> usuarioEstanteList = em.createQuery(format("from %s", UsuarioEstanteEntity.class.getSimpleName()))
                .getResultList();

        return usuarioEstanteList.stream()
                .filter(UsuarioEstanteEntity::getLido)
                .filter(p -> Objects.nonNull(p.getNota()) && p.getNota() > 0)
                .map(p -> new UsuarioRecomendadorVO(p.getIdUsuario(), p.getIdLivro(), p.getNota()))
                .collect(Collectors.toList());

    }

    public void deletaRecomendador(Integer idUsuario) {

        em.createNativeQuery("delete from recomendador_livro_usuario where id_usuario = :idUsuario")
                .setParameter("idUsuario", idUsuario)
                .executeUpdate();

    }

    public void insereRecomendador(UsuarioRecomendadorVO usuario) {

        StringBuilder sql = new StringBuilder(" INSERT INTO recomendador_livro_usuario(id_usuario,id_livro,nota) ")
                .append("VALUES (:idUsuario , :idLivro , :nota ) ");


        em.createNativeQuery(sql.toString())
                .setParameter("idUsuario", usuario.getIdUsuario())
                .setParameter("idLivro", usuario.getIdLivro())
                .setParameter("nota", usuario.getNota());


    }


    public void insereLivroRecomendado(LivroRecomendadoEntity livroRecomendado){
        em.merge(livroRecomendado);
    }


}
