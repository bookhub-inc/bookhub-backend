package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.entity.AvatarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AvatarDAO {


    @Autowired
    private EntityManager entityManager;


    public List<AvatarEntity> findAll(){
        return entityManager.createQuery("from avatar").getResultList();
    }

    public AvatarEntity find(Integer id){
        return entityManager.find(AvatarEntity.class,id);
    }

}
