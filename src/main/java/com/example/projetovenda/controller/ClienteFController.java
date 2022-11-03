/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetovenda.controller;

/**
 *
 * @author erick
 */



import com.example.projetovenda.model.entity.ClienteFisica;
import com.example.projetovenda.model.repository.ClienteFRepository;
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
@RequestMapping("clienteFisica")
public class ClienteFController {

    @Autowired
    ClienteFRepository repository;
    

    @GetMapping("/form")
    public String form(ClienteFisica pessoa){
        return "/clienteFisica/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {       
        model.addAttribute("clienteFisica", repository.pessoas());
        return new ModelAndView("/clienteFisica/list", model);
    }
    
    @PostMapping("/save")
    public ModelAndView save(ClienteFisica clienteFisica){
        repository.save(clienteFisica);
        return new ModelAndView("redirect:/clienteFisica/list");
    }
        
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/clienteFisica/list");
    }

    
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("clienteFisica", repository.pessoa(id));
        return new ModelAndView("/clienteFisica/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(ClienteFisica clienteFisica) {
        repository.update(clienteFisica);
        
        return new ModelAndView("redirect:/clienteFisica/list");
    }
    
}
