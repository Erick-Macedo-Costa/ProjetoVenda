/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetovenda.model.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author erick
 */
@Scope("session")
@Component
@Entity /*é utilizada para informar que uma classe também é uma entidade. A partir disso, a JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo nome no banco de dados,*/
@Table(name = "tb_venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    
    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itemvenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVenda> getItemvenda() {
        return itemvenda;
    }

    public void setItemvenda(List<ItemVenda> itemvenda) {
        this.itemvenda = itemvenda;
    }
    
    public Double total(){
     double total=0;
        for(int i=0;itemvenda.size()>i;i++){
            total = itemvenda.get(i).total() + total;
        }
    return total;
    }

    public void additemVenda(ItemVenda itemVenda) {
        this.itemvenda.add(itemVenda);
    }
}
