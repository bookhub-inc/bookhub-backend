package com.bookhub.backendbookhub.dao;


import com.bookhub.backendbookhub.api.vo.TopicoComentarioPutRequestVO;
import com.bookhub.backendbookhub.api.vo.TopicoPutRequestVO;
import com.bookhub.backendbookhub.entity.TopicoComentarioEntity;
import com.bookhub.backendbookhub.entity.TopicoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class TopicoDAO {

    @Autowired
    private EntityManager em;


    public TopicoEntity find(final Integer id) {
        return em.find(TopicoEntity.class, id);
    }

    public TopicoEntity save(final TopicoEntity topicoEntity) {
        return em.merge(topicoEntity);
    }

    public List<TopicoEntity> findByIdUsuario(final Integer idUsuario){

        String query = "select * from topico where id_usuario = :idUsuario order by dta_criacao desc";

        return em.createNativeQuery(query,TopicoEntity.class)
                .setParameter("idUsuario",idUsuario)
                .getResultList();

    }


    public List<TopicoEntity> findAll(){

        String query = "select * from topico order by dta_criacao desc";

        return em.createNativeQuery(query,TopicoEntity.class)
                .getResultList();

    }

    public List<TopicoComentarioEntity> findTopicoComentario(Integer idTopico) {

        String query = "select * from topico_comentario where id_topico = :idTopico";

        return em.createNativeQuery(query,TopicoComentarioEntity.class)
                .setParameter("idTopico",idTopico)
                .getResultList();

    }

    public TopicoComentarioEntity insereTopicoComentario(TopicoComentarioEntity topicoComentarioEntity){
        return em.merge(topicoComentarioEntity);
    }

    public void removeTopicoComentario(Integer idTopicoComentario){

        TopicoComentarioEntity topicoComentario = em.find(TopicoComentarioEntity.class,idTopicoComentario);
        em.remove(topicoComentario);

    }

    public void atualizaTopicoComentario(TopicoComentarioPutRequestVO topicoComentario){

        TopicoComentarioEntity resultado = em.find(TopicoComentarioEntity.class, topicoComentario.getId());

        resultado.setComentario(topicoComentario.getComentario());

    }

    public void atualizaTopico(TopicoPutRequestVO topico){

        TopicoEntity resultado = em.find(TopicoEntity.class, topico.getId());

        resultado.setDescricao(topico.getDescricao());

    }

    public void removeTopico(Integer idTopico) {
        String queryRemoveComentarios = "delete from topico_comentario where id_topico = :idTopico";

        String queryRemoveTopico = "delete from topico where id_topico = :idTopico";

        em.createNativeQuery(queryRemoveComentarios).setParameter("idTopico",idTopico).executeUpdate();

        em.createNativeQuery(queryRemoveTopico).setParameter("idTopico",idTopico).executeUpdate();

    }

}
