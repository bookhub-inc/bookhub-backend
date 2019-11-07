package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.api.vo.UsuarioPostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPostResponseVO;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import com.bookhub.backendbookhub.service.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@RestController
@Api(value="Usuario",tags = "Usuario",description = " ")
public class UsuarioAPI {

 @Autowired
 private UsuarioService usuarioService;

 @GetMapping("/")
 @ApiOperation(value = "Hello World ! ", hidden = true)
 public String index(){
  return "<h1>Hello World ! </h1>";

 }

 @ResponseStatus(CREATED)
 @ApiOperation(value = "Salva usuario",notes = "Insere um usuário no banco de dados", response = UsuarioPostResponseVO.class)
 @PostMapping("/usuario")
 public ResponseEntity<UsuarioPostResponseVO> save(@RequestBody final UsuarioPostRequestVO request) {
  UsuarioPostResponseVO respose = usuarioService.save(request);
   return new ResponseEntity<>(respose,CREATED);
 }

 @ResponseStatus(OK)
 @GetMapping("/usuario/{id}")
 public UsuarioEntity find(@ApiParam(example = "10",required = true) @PathVariable("id") final Integer id) {
   return usuarioService.find(id);
 }

 @ResponseStatus(NO_CONTENT)
 @ApiOperation(value = "Excluir um usuário", notes = "Excluir um usuário")
 @DeleteMapping("/usuario/{id}")
 public void delete(@ApiParam(example = "10",required = true) @PathVariable("id") final Integer id) {
  usuarioService.delete(id);
 }

 @ResponseStatus(OK)
 @ApiOperation(value="Busca todos os usuários cadastrados",notes = "Busca todos os usuários cadastrados", response = UsuarioEntity.class, responseContainer = "List")
 @GetMapping("/usuario")
 public List find() {
  return usuarioService.findAll();
 }


}
