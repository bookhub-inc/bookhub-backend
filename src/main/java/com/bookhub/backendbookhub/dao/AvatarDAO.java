package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.entity.AvatarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AvatarDAO {


    @Autowired
    private EntityManager entityManager;


    public List<AvatarEntity> findAll(){
        return entityManager.createQuery("from avatar").getResultList();
    }

}
