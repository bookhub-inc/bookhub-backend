package com.bookhub.backendbookhub.dao;


import com.bookhub.backendbookhub.api.vo.*;
import com.bookhub.backendbookhub.entity.TopicoComentarioEntity;
import com.bookhub.backendbookhub.entity.TopicoEntity;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class TopicoDAO {

    @Autowired
    private EntityManager em;


    public TopicoByIdResponseVO find(final Integer id) {

        TopicoEntity topicoEntity = em.find(TopicoEntity.class, id);

        UsuarioEntity usuario = em.find(UsuarioEntity.class,topicoEntity.getIdUsuario());

        return TopicoByIdResponseVO.builder()
                .id(topicoEntity.getId())
                .titulo(topicoEntity.getTitulo())
                .descricao(topicoEntity.getDescricao())
                .dataCriacao(topicoEntity.getDataCriacao())
                .spoiler(topicoEntity.getSpoiler())
                .login(usuario.getLogin())
                .build();


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


    public List<AllTopicoResponseVO> findAll(){

        String query = "select t.id,u.login, t.titulo,t.descricao,t.dta_criacao from topico  t\n" +
                "inner join usuario u on u.id = t.id_usuario\n" +
                "order by t.dta_criacao desc";

        List<Object[]> resultList = em.createNativeQuery(query)
                .getResultList();


        List<AllTopicoResponseVO> listaTopicos = new ArrayList<>();

        for(Object[] result : resultList){

            AllTopicoResponseVO vo = AllTopicoResponseVO.builder()
                    .id((Integer) result[0])
                    .login((String) result[1] )
                    .titulo((String) result[2])
                    .descricao((String) result[3])
                    .dataCriacao( ((Timestamp)result[4]).toLocalDateTime())
                    .build();

            listaTopicos.add(vo);


        }


        return listaTopicos;

    }

    public List<TopicoComentarioResponseVO> findTopicoComentario(Integer idTopico) {

        String query = "select tc.id,u.login, tc.comentario,tc.dta_comentario from topico_comentario tc \n " +
                " inner join usuario u on u.id = tc.id_usuario \n " +
                " where tc.id_topico = :idTopico";

        List<Object[]> resultList = em.createNativeQuery(query)
                .setParameter("idTopico", idTopico)
                .getResultList();

        List<TopicoComentarioResponseVO> listaComentarios = new ArrayList<>();

        for(Object[] result : resultList){

            TopicoComentarioResponseVO vo = TopicoComentarioResponseVO.builder()
                    .id((Integer) result[0])
                    .login((String) result[1] )
                    .comentario((String) result[2])
                    .dataComentario( ((Timestamp)result[3]).toLocalDateTime())
                    .build();

            listaComentarios.add(vo);


        }


        return listaComentarios;
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

        if(Objects.nonNull(topico.getTitulo())){
            resultado.setTitulo(topico.getTitulo());
        }

        if(Objects.nonNull(topico.getDescricao())) {
            resultado.setDescricao(topico.getDescricao());
        }
    }

    public void removeTopico(Integer idTopico) {
        String queryRemoveComentarios = "delete from topico_comentario where id_topico = :idTopico";

        String queryRemoveTopico = "delete from topico where id  = :idTopico";

        em.createNativeQuery(queryRemoveComentarios).setParameter("idTopico",idTopico).executeUpdate();

        em.createNativeQuery(queryRemoveTopico).setParameter("idTopico",idTopico).executeUpdate();

    }

}
