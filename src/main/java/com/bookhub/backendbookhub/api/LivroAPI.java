package com.bookhub.backendbookhub.api;


import com.bookhub.backendbookhub.api.vo.LivrosPostRequestVO;
import com.bookhub.backendbookhub.api.vo.TopicoPostRequestVO;
import com.bookhub.backendbookhub.api.vo.TopicoPostResponseVO;
import com.bookhub.backendbookhub.entity.LivroEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Api(value="Livro",tags = "Livro",description = " ")
public class LivroAPI {

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva livro",notes = "Insere um livro no banco de dados", response = TopicoPostResponseVO.class)
    @PostMapping("/livro")
    public ResponseEntity<LivroEntity> save(@RequestBody final LivrosPostRequestVO request) {
        LivroEntity respose = topicoService.save(request);
        return new ResponseEntity<>(respose,CREATED);
    }

}
