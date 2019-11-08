package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.entity.LivroCategoriaEntity;
import com.bookhub.backendbookhub.entity.LivroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LivroDAO  {

    @Autowired
    private EntityManager em;


    public LivroEntity save(final LivroEntity livroEntity){
        return em.merge(livroEntity);
    }

    public LivroEntity find(final Integer id){
        return em.find(LivroEntity.class,id);
    }

    public List listByNome(final String nome) {

        String sql = "select * from livro where nome like '%nome%'";

        return em.createNativeQuery(sql,LivroEntity.class)
                .setParameter("nome",nome)
                .getResultList();
    }

    public void adicionarLivroCategoria(LivroCategoriaEntity livroCategoriaEntity){
        em.merge(livroCategoriaEntity);
    }

    public void removerLivroCategoria(LivroCategoriaEntity livroCategoriaEntity){
        em.remove(livroCategoriaEntity);
    }

}
