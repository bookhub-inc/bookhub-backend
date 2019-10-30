package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.dao.UsuarioDAO;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import io.swagger.annotations.ApiOperation;
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
public class UsuarioAPI {

 @Autowired
 private UsuarioDAO usuarioDAO;

 @GetMapping("/")
 public String index(){
  return "<h1>Hello World ! </h1>";

 }

 @ResponseStatus(CREATED)
 @ApiOperation(value="Insere um usuário no banco de dados",notes = "Insere um usuário no banco de dados", response = UsuarioEntity.class)
 @PostMapping("/usuario")
 public UsuarioEntity save(@RequestBody final UsuarioEntity usuarioEntity) {
   return usuarioDAO.save(usuarioEntity);
 }

 @ResponseStatus(OK)
 @GetMapping("/usuario/{id}")
 public UsuarioEntity find(@PathVariable("id") final Long id) {
   return usuarioDAO.find(id);
 }

 @ResponseStatus(NO_CONTENT)
 @ApiOperation(value = "Excluir um usuário", notes = "Excluir um usuário")
 @DeleteMapping("/usuario/{id}")
 public void delete(@PathVariable("id") final Long id) {
  usuarioDAO.delete(id);
 }

 @ResponseStatus(OK)
 @ApiOperation(value="Busca todos os usuários cadastrados",notes = "Busca todos os usuários cadastrados", response = UsuarioEntity.class, responseContainer = "List")
 @GetMapping("/usuario")
 public List find() {
  return usuarioDAO.findAll();
 }


}
