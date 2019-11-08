package com.bookhub.backendbookhub.dao;


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

    public List findByIdUsuario(final Integer idUsuario){

        String query = "select * from topico where id_usuario = :idUsuario order by dta_criacao desc";

        return em.createNativeQuery(query,TopicoEntity.class)
                .setParameter("idUsuario",idUsuario)
                .getResultList();

    }



}
