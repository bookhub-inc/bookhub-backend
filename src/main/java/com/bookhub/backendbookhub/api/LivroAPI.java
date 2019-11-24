package com.bookhub.backendbookhub.api;


import com.bookhub.backendbookhub.api.vo.LivrosPostRequestVO;
import com.bookhub.backendbookhub.entity.LivroEntity;
import com.bookhub.backendbookhub.service.LivroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(value="Livro",tags = "Livro",description = " ")
public class LivroAPI {

    @Autowired
    private LivroService livroService;

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva Livro",notes = "Insere um livro no banco de dados", response = LivrosPostRequestVO.class)
    @PostMapping("/livro")
    public ResponseEntity<LivroEntity> save(@RequestBody final LivrosPostRequestVO request) {
        LivroEntity respose = livroService.save(request);
        return new ResponseEntity<>(respose,CREATED);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Busca livros",notes = "Busca todos os livros aprovados podendo usar como filtro nome e/ou autor", response = LivroEntity.class)
    @GetMapping("/livro")
    public ResponseEntity<List<LivroEntity>> findAllLivros(@RequestParam(name = "nome",required = false) String nome, @RequestParam(name = "autor",required = false) String autor) {
        return new ResponseEntity<>(livroService.listByNomeAndAutor(nome,autor),OK);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Busca livro por id",notes = "Busca livro por id", response = LivroEntity.class)
    @GetMapping("/livro/{id}")
    public ResponseEntity<LivroEntity> findAllLivros(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(livroService.find(id),OK);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Remove uma categoria de um livro",notes = "Remove uma categoria de um livro")
    @DeleteMapping("/livro/categoria")
    public ResponseEntity removeLivroCategoria(@RequestParam(name = "idCategoria",required = true) Integer idCategoria, @RequestParam(name = "idLivro",required = true) Integer idLivro) {
        livroService.removerLivroCategoria(idCategoria,idLivro);
        return new ResponseEntity<>(OK);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Adiciona uma categoria de um livro",notes = "Adiciona uma categoria de um livro")
    @PostMapping("/livro/categoria")
    public ResponseEntity adicionaLivroCategoria(@RequestParam(name = "idCategoria",required = true) Integer idCategoria, @RequestParam(name = "idLivro",required = true) Integer idLivro) {
        livroService.adicionarLivroCategoria(idCategoria,idLivro);
        return new ResponseEntity<>(CREATED);
    }


}
