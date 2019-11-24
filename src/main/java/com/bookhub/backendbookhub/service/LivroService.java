package com.bookhub.backendbookhub.service;

import com.bookhub.backendbookhub.api.vo.LivrosPostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioEstantePostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioEstanteResponseVO;
import com.bookhub.backendbookhub.dao.LivroDAO;
import com.bookhub.backendbookhub.entity.LivroCategoriaEntity;
import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        livroDAO.adicionarLivroCategoria(new LivroCategoriaEntity(null,idLivro,idCategoria));
    }

    @Transactional
    public void removerLivroCategoria(Integer idCategoria, Integer idLivro){
        livroDAO.removerLivroCategoria(new LivroCategoriaEntity(null,idLivro,idCategoria));
    }


    @Transactional
    public void adicionarUsuarioEstante(UsuarioEstantePostRequestVO request){
        livroDAO.adicionarUsuarioEstante(request.toEntity());
    }

    @Transactional
    public void removerUsuarioEstante(Integer idUsuarioEstante){
        // TODO Colocar mensagem caso não retorne nada !
        UsuarioEstanteEntity usuarioEstante =livroDAO.findUsuarioEstante(idUsuarioEstante);
        livroDAO.removerUsuarioEstante(usuarioEstante);
    }

    public List<LivroEntity> listByNomeAndAutor(final String nome, final String autor) {
        return livroDAO.listByNomeAndAutor(nome,autor);
    }

    public List<UsuarioEstanteResponseVO> listaUsuarioEstante(Integer idUsuario) {
        return livroDAO.listaUsuarioEstante(idUsuario);
    }

    public LivroEntity find(Integer id){
        return livroDAO.find(id);
    }

}
