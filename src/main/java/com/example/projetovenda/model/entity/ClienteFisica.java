/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetovenda.model.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author erick
 */
@Entity
@Table(name = "tb_cliente_fisica")
public class ClienteFisica extends Cliente{

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    private String cpf;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Venda> vendas;

    public void addVenda(Venda venda){
        this.vendas.add(venda);
    }
    
}
