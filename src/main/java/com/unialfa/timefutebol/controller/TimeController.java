package com.unialfa.timefutebol.controller;

import com.unialfa.timefutebol.model.Time;
import com.unialfa.timefutebol.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("time")
public class TimeController {

    private static final String FORMULARIO_BOASVINDAS = "UniALFA - Formulário de Times";
    private static final String LISTA_BOASVINDAS = "UniALFA - Lista de Times";

    @Autowired
    private TimeService service;

    @RequestMapping("novo")
    public String iniciar(Time time, Model model) {
        model.addAttribute("boasVindas", FORMULARIO_BOASVINDAS);
        return "time/formulario";
    }

    @PostMapping("salvar")
    public String salvar(Time time, Model model) {
        service.salvarTime(time);
        return "redirect:/time/listar";
    }

    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("boasVindas", LISTA_BOASVINDAS);
        model.addAttribute("listaTimes", service.listarTodos());
        return "time/lista";
    }

    @GetMapping("alterar/{id}")
    public String alterar(@PathVariable Integer id, Model model) {
        model.addAttribute("boasVindas", FORMULARIO_BOASVINDAS);
        model.addAttribute("time", service.buscarPorId(id));
        return "time/formulario";
    }

    @PostMapping("remover")
    public String remover(Time time, Model model) {
        service.remover(time);
        return "redirect:/time/listar";
    }
}
