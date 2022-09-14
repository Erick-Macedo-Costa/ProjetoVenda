/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetovenda.controller;

/**
 *
 * @author erick
 */

import com.example.projetovenda.model.entity.Venda;
import com.example.projetovenda.model.repository.VendaRepository;
import javax.transaction.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
@Controller
@RequestMapping("venda")
public class VendaController {

    @Autowired
    VendaRepository repository;
    
    @GetMapping("/form")
    public String form(Venda venda){
        return "/venda/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("venda", repository.vendas());
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

}
