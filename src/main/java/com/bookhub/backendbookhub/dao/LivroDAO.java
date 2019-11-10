package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.entity.LivroCategoriaEntity;
import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

@Repository
public class LivroDAO {

    @Autowired
    private EntityManager em;


    public LivroEntity save(final LivroEntity livroEntity) {
        return em.merge(livroEntity);
    }

    public LivroEntity find(final Integer id) {
        return em.find(LivroEntity.class, id);
    }

    public List listByNome(final String nome) {

        String sql = "select * from livro where nome like '%nome%'";

        return em.createNativeQuery(sql, LivroEntity.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List findAll() {

        String sql = "select * from livro ";

        return em.createNativeQuery(sql, LivroEntity.class)
                .getResultList();
    }

    public void adicionarLivroCategoria(LivroCategoriaEntity livroCategoriaEntity) {
        em.merge(livroCategoriaEntity);
    }

    public void removerLivroCategoria(LivroCategoriaEntity livroCategoriaEntity) {

        String sql = " Delete from livxcat where id_livro =:idLivro and id_cat = :idCat";

        em.createNativeQuery(sql)
                .setParameter("idCat", livroCategoriaEntity.getIdCategoria())
                .setParameter("idLivro", livroCategoriaEntity.getIdLivro())
                .executeUpdate();
    }

    public UsuarioEstanteEntity adicionarUsuarioEstante(UsuarioEstanteEntity usuarioEstanteEntity) {
        return em.merge(usuarioEstanteEntity);
    }

    public void removerUsuarioEstante(UsuarioEstanteEntity usuarioEstanteEntity) {


        String sql = " Delete from usuario_estante where id_livro =:idLivro and id_usuario = :idUsuario";

        em.createNativeQuery(sql)
                .setParameter("idCat", usuarioEstanteEntity.getIdUsuario())
                .setParameter("idLivro", usuarioEstanteEntity.getIdLivro())
                .executeUpdate();

    }

    public UsuarioEstanteEntity findUsuarioEstante(Integer id) {
        return em.find(UsuarioEstanteEntity.class, id);
    }


    public List<LivroEntity> listByNomeAndAutor(final String nome, final String autor) {

        StringBuilder sql = new StringBuilder("select * from livro where aprovado = 1 ");

        if (Objects.nonNull(nome)) {
            sql.append(" upper(nome) like  upper(%:nome%) ");
        }

        if (Objects.nonNull(autor)) {
            sql.append(" AND upper(%:autor%) ");
        }

        Query query = em.createNativeQuery(sql.toString(), LivroEntity.class);

        if (Objects.nonNull(nome)) {
            query.setParameter("nome", nome);
        }

        if (Objects.nonNull(autor)) {
            query.setParameter("autor", autor);
        }

        return query.getResultList();
    }

}