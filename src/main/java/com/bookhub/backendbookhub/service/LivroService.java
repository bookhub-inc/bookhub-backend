package com.bookhub.backendbookhub.service;

import com.bookhub.backendbookhub.api.vo.LivrosPostRequestVO;
import com.bookhub.backendbookhub.dao.LivroDAO;
import com.bookhub.backendbookhub.entity.LivroCategoriaEntity;
import com.bookhub.backendbookhub.entity.LivroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    @Autowired
    private LivroDAO livroDAO;

    @Autowired
    private LivroService self;

    @Transactional
    public LivroEntity save(final LivrosPostRequestVO request){
        LivroEntity livro = livroDAO.save(request.toEntity());

        request.getCategorias().forEach(idCategoria ->
                self.adicionarLivroCategoria(idCategoria,livro.getId())
        );

        return livro;

    }

    @Transactional
    public void adicionarLivroCategoria(Integer idCategoria, Integer idLivro){
        livroDAO.adicionarLivroCategoria(new LivroCategoriaEntity(idLivro,idCategoria));
    }

    @Transactional
    public void removerLivroCategoria(Integer idCategoria, Integer idLivro){
        livroDAO.removerLivroCategoria(new LivroCategoriaEntity(idLivro,idCategoria));
    }



}
