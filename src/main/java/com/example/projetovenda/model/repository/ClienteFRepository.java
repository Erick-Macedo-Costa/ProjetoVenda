/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetovenda.model.repository;
import com.example.projetovenda.model.entity.ClienteFisica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author erick
 */
@Repository
public class ClienteFRepository {
    
     @PersistenceContext
    private EntityManager em;

    public void save(ClienteFisica pessoa){
        em.persist(pessoa);
    }

    public ClienteFisica pessoa(Long id){
        return em.find(ClienteFisica.class, id);
    }

    public List<ClienteFisica> pessoas(){
        Query query = em.createQuery("from ClienteFisica");
        return query.getResultList();
    }

    public void remove(Long id){
        ClienteFisica p = em.find(ClienteFisica.class, id);
        em.remove(p);
    }

    public void update(ClienteFisica pessoa){
        em.merge(pessoa);
    }
}