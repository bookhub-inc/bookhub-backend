package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.api.vo.UsuarioEstantePostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioEstanteResponseVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPostResponseVO;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import com.bookhub.backendbookhub.entity.UsuarioEstanteEntity;
import com.bookhub.backendbookhub.exception.CampoExistenteException;
import com.bookhub.backendbookhub.exception.UsuarioExistenteException;
import com.bookhub.backendbookhub.exception.vo.CampoExistente;
import com.bookhub.backendbookhub.service.LivroService;
import com.bookhub.backendbookhub.service.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@RestController
@Api(value = "Usuario", tags = "Usuario", description = " ")
public class UsuarioAPI {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @GetMapping("/")
    @ApiOperation(value = "Hello World ! ", hidden = true)
    public RedirectView index() {
        return new RedirectView("swagger-ui.html");

    }

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva usuario", notes = "Insere um usuário no banco de dados", response = UsuarioPostResponseVO.class)
    @PostMapping("/usuario")
    public ResponseEntity save(@RequestBody final UsuarioPostRequestVO request) {

        try {
            UsuarioPostResponseVO respose = usuarioService.save(request);
            return new ResponseEntity<>(respose, CREATED);
        } catch (Exception e) {
            if (e instanceof CampoExistenteException) {
                return new ResponseEntity<>(new CampoExistente((CampoExistenteException) e), INTERNAL_SERVER_ERROR);
            }
            if (e instanceof UsuarioExistenteException) {
                return new ResponseEntity<>(((UsuarioExistenteException) e).toString(), INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Erro interno no sistema, entrar em contato com um administrador", INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(OK)
    @GetMapping("/usuario/{id}")
    public UsuarioEntity find(@ApiParam(example = "10", required = true) @PathVariable("id") final Integer id) {
        return usuarioService.find(id);
    }

    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Excluir um usuário", notes = "Excluir um usuário")
    @DeleteMapping("/usuario/{id}")
    public void delete(@ApiParam(example = "10", required = true) @PathVariable("id") final Integer id) {
        usuarioService.delete(id);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Busca todos os usuários cadastrados", notes = "Busca todos os usuários cadastrados", response = UsuarioEntity.class, responseContainer = "List")
    @GetMapping("/usuario")
    public List find() {
        return usuarioService.findAll();
    }

    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salva um livro na estante do usuário", notes = "Salva um livro na estante do usuário", response = UsuarioPostResponseVO.class)
    @PostMapping("/usuario/estante")
    public ResponseEntity saveUsuarioEstante(@RequestBody final UsuarioEstantePostRequestVO request) {

        livroService.adicionarUsuarioEstante(request);
        return new ResponseEntity<>(CREATED);
    }

    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Excluir um livro da estante do usuário", notes = "Excluir um livro da estante do usuário")
    @DeleteMapping("/usuario/estante/{id}")
    public void removeLivroEstante(@ApiParam(example = "10", required = true) @PathVariable("id") final Integer id) {
        livroService.removerUsuarioEstante(id);
    }

    @ResponseStatus(OK)
    @ApiOperation(value = "Retorna a estante do usuario", notes = "Retorna a estante do usuario")
    @GetMapping("/usuario/estante/{id}")
    public List<UsuarioEstanteResponseVO> listaEstante(@ApiParam(example = "1", required = true) @PathVariable("id") final Integer id) {
        return livroService.listaUsuarioEstante(id);
    }


}


