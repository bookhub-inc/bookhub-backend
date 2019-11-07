package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.api.vo.TopicoPostRequestVO;
import com.bookhub.backendbookhub.api.vo.TopicoPostResponseVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPostResponseVO;
import com.bookhub.backendbookhub.entity.TopicoEntity;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
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
    @GetMapping("/topico/{id}")
    public TopicoEntity find(@ApiParam(example = "10",required = true) @PathVariable("id") final Integer id) {
        return topicoService.find(id);
    }

    @ResponseStatus(OK)
    @GetMapping("/topico/usuario/{id}")
    public List findByIdUsuario(@ApiParam(example = "10",required = true) @PathVariable("id") final Integer id) {
        return topicoService.findByIdUsuario(id);
    }



}
