/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetovenda.model.repository;


import com.example.projetovenda.model.entity.Venda;
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
public class VendaRepository {
  
    @PersistenceContext /* é um local onde ficam armazenados os objetos */
    private EntityManager em;

    public void save(Venda venda){
        em.persist(venda);
    }

    public Venda produto(Long id){
        return em.find(Venda.class, id);
    }

    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public void remove(Long id){
        Venda p = em.find(Venda.class, id);
        em.remove(p);
    }

    public void update(Venda venda){
        em.merge(venda);
    }

    public Object venda(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

