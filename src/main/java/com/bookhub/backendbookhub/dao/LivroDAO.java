package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.api.vo.LivrosPutRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioEstanteResponseVO;
import com.bookhub.backendbookhub.entity.LivroCategoriaEntity;
import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class LivroDAO {

    public static final String CORINGA = "%";
    @Autowired
    private EntityManager em;

    public void removeLivro(Integer id) {

        LivroEntity livro = find(id);

        em.remove(livro);
    }

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

    public void adicionarUsuarioEstante(UsuarioEstanteEntity usuarioEstanteEntity) {
        em.merge(usuarioEstanteEntity);
    }

    public void removerUsuarioEstante(UsuarioEstanteEntity usuarioEstanteEntity) {


        String sql = " Delete from usuario_estante where id_livro =:idLivro and id_usuario = :idUsuario";

        em.createNativeQuery(sql)
                .setParameter("idUsuario", usuarioEstanteEntity.getIdUsuario())
                .setParameter("idLivro", usuarioEstanteEntity.getIdLivro())
                .executeUpdate();

    }

    public List<UsuarioEstanteResponseVO> listaUsuarioEstante(Integer idUsuario){

        String sql = "\n" +
                "select l.id,l.nome,l.autor,l.descricao,l.url_livro,l.n_paginas,ae.nota,ae.lido,ae.comprado,ae.id_usuario  from usuario_estante ae\n" +
                "                inner join livro l on l.id = ae.id_livro\n" +
                "                where ae.id_usuario = :idUsuario";

        List<Object[]> lista =  em.createNativeQuery(sql)
                .setParameter("idUsuario",idUsuario)
                .getResultList();

        List<UsuarioEstanteResponseVO> result = new ArrayList<>();


        for(Object[] ob : lista ){

            UsuarioEstanteResponseVO vo = UsuarioEstanteResponseVO.builder()
                    .id((Integer) ob[0])
                    .nome((String) ob[1])
                    .autor((String) ob[2])
                    .descricao((String) ob[3])
                    .url((String) ob[4])
                    .nPaginas( (Integer) ob[5])
                    .nota((Integer) ob[6])
                    .lido((Boolean) ob[7])
                    .comprado((Boolean) ob[8])
                    .idUsuario((Integer) ob[9])
                    .build();

            result.add(vo);

        }


        return result;
    }


    public UsuarioEstanteEntity findUsuarioEstante(Integer id) {
        return em.find(UsuarioEstanteEntity.class, id);
    }


    public List<LivroEntity> listByNomeAndAutor(final String nome, final String autor, final Boolean aprovado) {

        StringBuilder sql = new StringBuilder("select * from livro where 1=1 ");



        if (Objects.nonNull(aprovado)) {
            sql.append(" AND aprovado = :aprovado ");
        }


        if (Objects.nonNull(nome)) {
            sql.append(" AND upper(nome) like  upper(:nome ) ");
        }

        if (Objects.nonNull(autor)) {
            sql.append(" AND upper(autor) like upper(:autor) ");
        }

        Query query = em.createNativeQuery(sql.toString(), LivroEntity.class);

        if (Objects.nonNull(nome)) {
            query.setParameter("nome", CORINGA.concat(nome).concat(CORINGA));
        }


        if (Objects.nonNull(autor)) {
            query.setParameter("autor", CORINGA.concat(autor).concat(CORINGA));
        }

        if (Objects.nonNull(aprovado)) {
            query.setParameter("aprovado",aprovado?1:0);
        }

        return query.getResultList();
    }

    public void alteraLivro(LivrosPutRequestVO livrosPutRequestVO) {

        LivroEntity livro = em.find(LivroEntity.class,livrosPutRequestVO.getId());
        livro.setDataAtualizacao(LocalDateTime.now());

        if(Objects.nonNull(livrosPutRequestVO.getAprovado())){
            livro.setAprovado(livrosPutRequestVO.getAprovado());
        }

        if(Objects.nonNull(livrosPutRequestVO.getMotivo())){
            livro.setMotivo(livrosPutRequestVO.getMotivo());
        }


        if(Objects.nonNull(livrosPutRequestVO.getDataLancamento())){
            livro.setDataLancamento(livrosPutRequestVO.getDataLancamento());
        }

        if(Objects.nonNull(livrosPutRequestVO.getAutor())){
            livro.setAutor(livrosPutRequestVO.getAutor());
        }

        if(Objects.nonNull(livrosPutRequestVO.getEditora())){
            livro.setEditora(livrosPutRequestVO.getEditora());
        }

        if(Objects.nonNull(livrosPutRequestVO.getNome())){
            livro.setNome(livrosPutRequestVO.getNome());
        }

        if(Objects.nonNull(livrosPutRequestVO.getNPaginas())){
            livro.setNPaginas(livrosPutRequestVO.getNPaginas());
        }

        if(Objects.nonNull(livrosPutRequestVO.getUrl())){
            livro.setUrl(livrosPutRequestVO.getUrl());
        }

    }


}
