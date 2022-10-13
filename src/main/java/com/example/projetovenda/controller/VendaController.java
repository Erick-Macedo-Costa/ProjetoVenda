/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetovenda.controller;

/**
 *
 * @author erick
 */

import com.example.projetovenda.model.entity.ItemVenda;
import com.example.projetovenda.model.entity.Venda;
import com.example.projetovenda.model.repository.ProdutoRepository;
import com.example.projetovenda.model.repository.VendaRepository;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Transactional /*trabalha dentro do escopo de uma transação no banco de dados, a transação do banco de dados ocorre dentro do PersistenceContext */
@Controller
@Scope("request")
@RequestMapping("venda")
public class VendaController {

    @Autowired /* indica um ponto aonde a injeção automática deve ser aplicada. Esta pode ser usada em métodos, atributos e construtores.*/
    VendaRepository repository;
    
    @Autowired /* indica um ponto aonde a injeção automática deve ser aplicada. Esta pode ser usada em métodos, atributos e construtores.*/
    ProdutoRepository reposiprod;
    
    @Autowired
    Venda venda;
    
    @GetMapping("/form")
    public String form(Venda venda){
        return "/venda/form";
    }
    
    @PostMapping("/itemsave")
    public String itemsave (ItemVenda itemVenda){
        itemVenda.setProduto(reposiprod.produto(itemVenda.getProduto().getId()));
        itemVenda.setVenda(venda);
        venda.additemVenda(itemVenda);
        return "redirect:/produtos/listaitens";
        
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("/venda/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Venda venda){
        repository.save(venda);
        return new ModelAndView("redirect:/venda/list");
    }
        
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/venda/list");
    }

    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/venda/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        repository.update(venda);
        return new ModelAndView("redirect:/venda/list");
    }
    @GetMapping("/carrinho")
    public ModelAndView carrinho(){
        return new ModelAndView("/venda/carrinho");
    }
    @GetMapping("/comprar")
    public ModelAndView comprar(HttpSession session){
        repository.save(venda);
        session.invalidate();
        return new ModelAndView("redirect:/venda/list");
    }
    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/venda/detalhes", model);
    }        
}
