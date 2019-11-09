package com.bookhub.backendbookhub.service;

import com.bookhub.backendbookhub.api.vo.UsuarioPostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPostResponseVO;
import com.bookhub.backendbookhub.dao.UsuarioDAO;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;


    public UsuarioPostResponseVO save(UsuarioPostRequestVO usuarioPostRequestVO){
        UsuarioEntity usuario = usuarioDAO.save(usuarioPostRequestVO.toUsuarioEntity());

        return UsuarioPostResponseVO.builder()
                .email(usuario.getEmail())
                .id(usuario.getId())
                .idAvatar(usuario.getIdAvatar())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .usuario(usuario.getLogin())
                .build();

    }

    public List findAll(){
        return usuarioDAO.findAll();
    }

    public void delete(final Integer id) {
        usuarioDAO.delete(id);
    }

    public UsuarioEntity find(final Integer id){
        return usuarioDAO.find(id);
    }


}
