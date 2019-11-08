package com.bookhub.backendbookhub.service;

import com.bookhub.backendbookhub.api.vo.TopicoPostRequestVO;
import com.bookhub.backendbookhub.api.vo.TopicoPostResponseVO;
import com.bookhub.backendbookhub.dao.TopicoDAO;
import com.bookhub.backendbookhub.entity.TopicoEntity;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoDAO topicoDAO;


    public TopicoPostResponseVO save(TopicoPostRequestVO topico) {


        TopicoEntity entity = topicoDAO.save(topico.toTopicoEntity());


        return TopicoPostResponseVO.builder()
                .id(entity.getId())
                .idUsuario(entity.getIdUsuario())
                .spoiler(entity.getSpoiler())
                .titulo(entity.getTitulo())
                .build();
    }

    public List findByIdUsuario(final Integer idUsuario){
        return topicoDAO.findByIdUsuario(idUsuario);
    }

    public TopicoEntity find (final Integer id){
        return topicoDAO.find(id);
    }


}
