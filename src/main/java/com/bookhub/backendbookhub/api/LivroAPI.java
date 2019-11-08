package com.bookhub.backendbookhub.api;


import com.bookhub.backendbookhub.api.vo.LivrosPostRequestVO;
import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.service.LivroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Api(value="Livro",tags = "Livro",description = " ")
public class LivroAPI {

    @Autowired
    private LivroService livroService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva idLivro",notes = "Insere um idLivro no banco de dados", response = LivrosPostRequestVO.class)
    @PostMapping("/idLivro")
    public ResponseEntity<LivroEntity> save(@RequestBody final LivrosPostRequestVO request) {
        LivroEntity respose = livroService.save(request);
        return new ResponseEntity<>(respose,CREATED);
    }

}
