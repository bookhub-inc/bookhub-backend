package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;
import com.bookhub.backendbookhub.recomendador.UsuarioRecomendadorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioEstanteDAO {

    @Autowired
    private EntityManager em;

    public List findAllRecomendador(){


        List<UsuarioEstanteEntity> usuarioEstanteList = em.createQuery("from " + UsuarioEstanteEntity.class.getSimpleName())
                .getResultList();

        return usuarioEstanteList.stream()
                .filter(UsuarioEstanteEntity::getLido)
                .map(p -> new UsuarioRecomendadorVO(p.getIdUsuario(),p.getIdLivro(),p.getNota()))
                .collect(Collectors.toList());


    }


}
