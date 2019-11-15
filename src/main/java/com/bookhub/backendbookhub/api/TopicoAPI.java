package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.api.vo.TopicoPostRequestVO;
import com.bookhub.backendbookhub.api.vo.TopicoPostResponseVO;
import com.bookhub.backendbookhub.entity.TopicoComentarioEntity;
import com.bookhub.backendbookhub.entity.TopicoEntity;
import com.bookhub.backendbookhub.service.TopicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(value="Topico",tags = "Topico",description = " ")
public class TopicoAPI {


    @Autowired
    private TopicoService topicoService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva topico",notes = "Insere um tópico no banco de dados", response = TopicoPostResponseVO.class)
    @PostMapping("/topico")
    public ResponseEntity<TopicoPostResponseVO> save(@RequestBody final TopicoPostRequestVO request) {
        TopicoPostResponseVO respose = topicoService.save(request);
        return new ResponseEntity<>(respose,CREATED);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna um topico buscando por ID", notes = "Retorna um topico buscando por ID")
    @GetMapping("/topico/{id}")
    public TopicoEntity find(@ApiParam(example = "10",required = true) @PathVariable("id") final Integer id) {
        return topicoService.find(id);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna todos os topicos de um usuario especifico", notes = "Retorna todos os topicos de um usuario especifico")
    @GetMapping("/topico/usuario/{idUsuario}")
    public List<TopicoEntity> findByIdUsuario(@ApiParam(example = "10",required = true) @PathVariable("idUsuario") final Integer idUsuario) {
        return topicoService.findByIdUsuario(idUsuario);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna todos os comentarios de um topicos", notes = "Retorna todos os comentarios de um topicos")
    @GetMapping("/topico/{idTopico}/comentario")
    public List<TopicoComentarioEntity> findComentarioTopico(@ApiParam(example = "1",required = true) @PathVariable("idTopico") final Integer idTopico) {
        return topicoService.findTopicoComentario(idTopico);
    }


    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna todos os topicos", notes = "Retorna todos os topicos")
    @GetMapping("/topico")
    public List<TopicoEntity> findAll() {
        return topicoService.findAll();
    }




}
