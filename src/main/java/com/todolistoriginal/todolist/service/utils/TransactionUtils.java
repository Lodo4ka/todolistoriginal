package com.todolistoriginal.todolist.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Function;

@Component
public class TransactionUtils {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public <R> R performInsideTransaction(Function<EntityManager, R> function){
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        try{
            entityManager.getTransaction().begin();

            R result = function.apply(entityManager);

            entityManager.getTransaction().commit();

            return result;
        }
        finally {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }

            if(entityManager.isOpen()){
                entityManager.close();
            }
        }
    }
}
