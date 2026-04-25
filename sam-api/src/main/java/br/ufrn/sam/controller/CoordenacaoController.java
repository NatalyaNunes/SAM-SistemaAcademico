package br.ufrn.sam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coordenacao")
public class CoordenacaoController {
    @GetMapping("/dashboardCoordenacao")
    public String dashboardCoordenacao(Model model) {
        return "pages/dashboardCoordenacao";
    }

    @GetMapping("/turmasOciosas")
    public String ociosas(Model model) {
        return "pages/turmasOciosas";
    }

    @GetMapping("/formacao")
    public String formacao(Model model) {
        return "pages/formacao";
    }

    @GetMapping("/configuracoes")
    public String configuracoes(Model model) {
        return "pages/configuracoesCoordenacao";
    }
}
