package br.ufrn.sam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.sam.model.DisciplinaModel;
import br.ufrn.sam.model.ProfessorModel;
import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.service.DisciplinaService;
import br.ufrn.sam.service.ProfessorService;
import br.ufrn.sam.service.TurmaService;

@Controller
@RequestMapping("/coordenacao")
public class CoordenacaoController {

    private final TurmaService turmaService;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;

    public CoordenacaoController(TurmaService turmaService, ProfessorService professorService, DisciplinaService disciplinaService) {
        this.turmaService = turmaService;
        this.professorService = professorService;
        this.disciplinaService = disciplinaService;
    }
    
    @GetMapping("/dashboard")
    public String dashboardCoordenacao(Model model) {
        return "pages/dashboardCoordenacao";
    }

    @GetMapping("/turmasOciosas")
    public String ociosas(Model model) {
        return "pages/turmasOciosas";
    }

    @GetMapping("/configuracoes")
    public String configuracoes(Model model) {
        List<TurmaModel> turmas = turmaService.listarTodas();
        List<ProfessorModel> professores = professorService.listarTodos();
        List<DisciplinaModel> disciplinas = disciplinaService.listarTodas();

        
        model.addAttribute("turmas", turmas);
        model.addAttribute("professores", professores);
        model.addAttribute("disciplinas", disciplinas);
        return "pages/configuracoesCoordenacao";
    }
}
