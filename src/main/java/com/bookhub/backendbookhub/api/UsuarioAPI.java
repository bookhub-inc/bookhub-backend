package com.bookhub.backendbookhub.api;

import com.bookhub.backendbookhub.dao.UsuarioDAO;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

 @PostMapping("/usuario")
 public ResponseEntity save(@RequestBody final UsuarioEntity usuarioEntity) {
  try {
   return ResponseEntity.ok(usuarioDAO.save(usuarioEntity));
  } catch (Exception e) {
   String msg = e.getMessage();
   return ResponseEntity.badRequest().body(msg);
  }
 }

 @GetMapping("/usuario/{id}")
 public ResponseEntity find(@PathVariable("id") final Long id) {
  try {
   return ResponseEntity.ok(usuarioDAO.find(id));
  } catch (Exception e) {
   String msg = e.getMessage();
   return ResponseEntity.badRequest().body(msg);
  }
 }

 @DeleteMapping("/usuario/{id}")
 public ResponseEntity delete(@PathVariable("id") final Long id) {
  usuarioDAO.delete(id);
  return ResponseEntity.ok("Sucesso");
 }

 @GetMapping("/usuario")
 public ResponseEntity find() {
  return ResponseEntity.ok(usuarioDAO.findAll());
 }


}
