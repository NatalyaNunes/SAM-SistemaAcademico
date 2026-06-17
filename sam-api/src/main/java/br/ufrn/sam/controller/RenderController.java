package br.ufrn.sam.controller;

import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.service.TurmaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class RenderController {

    private final TurmaService turmaService;

    public RenderController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/dashboardAluno")
    public String dashboardAluno(Model model) {
        List<TurmaModel> turmasInteresse = turmaService.listarTodas();
        
        Set<Integer> idsEmConflito = turmaService.verificarConflitos(turmasInteresse);
        
        model.addAttribute("turmasInteresse", turmasInteresse);
        model.addAttribute("idsEmConflito", idsEmConflito);
        
        return "pages/dashboardAluno";
    }

    @GetMapping("/turmas")
    public String turmas(Model model) {
        model.addAttribute("todasAsTurmas", turmaService.listarTodas());
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