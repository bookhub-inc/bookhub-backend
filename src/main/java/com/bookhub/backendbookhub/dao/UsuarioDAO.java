package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@Repository
@Transactional
public class UsuarioDAO {

 @Autowired
 private EntityManager em;

 public UsuarioEntity find(final Integer id) {
  return em.find(UsuarioEntity.class, id);
 }

 public List findAll() {
  return em.createQuery("from " + UsuarioEntity.class.getSimpleName()).getResultList();
 }

 public UsuarioEntity save(final UsuarioEntity usuarioEntity) {
  return em.merge(usuarioEntity);
 }

 public void delete(final Integer id) {
  final UsuarioEntity user = find(id);
  em.remove(user);
 }


}
