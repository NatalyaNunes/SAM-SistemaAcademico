package br.ufrn.sam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenderController {
    @GetMapping("/dashboardAluno")
    public String dashboardAluno(Model model) {
        return "pages/dashboardAluno";
    }
    @GetMapping("/turmas")
    public String turmas(Model model) {
        return "pages/turmas";
    }
    @GetMapping("/ranking")
    public String ranking(Model model) {
        return "pages/ranking";
    }
    @GetMapping("/configuracoes")
    public String configuracoes(Model model) {
        return "pages/configuracoes";
    }
}
