package br.ufrn.sam.controller;

import java.util.List;

import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.service.TurmaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.model.InteresseModel;
import br.ufrn.sam.model.PessoaModel;
import br.ufrn.sam.service.InteresseService;
import jakarta.servlet.http.HttpSession;

@Controller
public class RenderController {

    private final TurmaService turmaService;
    private final InteresseService interesseService;

    public RenderController(TurmaService turmaService, InteresseService interesseService) {
        this.turmaService = turmaService;
        this.interesseService = interesseService;
    }

    @GetMapping("/dashboardAluno")
    public String dashboardAluno(HttpSession session, Model model) {
        PessoaModel usuarioLogado = (PessoaModel) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !usuarioLogado.getIsAluno()) {
            return "redirect:/sam"; // Se não tiver logado, manda pro login
        }

        AlunoModel aluno = (AlunoModel) usuarioLogado;

        List<TurmaModel> turmasDoAluno = turmaService.listarTurmasPorInteresseAluno(aluno.getMatricula());

        Set<Integer> idsEmConflito = turmaService.verificarConflitos(turmasDoAluno);
        List<InteresseModel> meusInteresses = interesseService.listarPorAluno(aluno.getMatricula());

        model.addAttribute("aluno", aluno);
        model.addAttribute("turmasInteresse", turmasDoAluno);
        model.addAttribute("idsEmConflito", idsEmConflito);
        model.addAttribute("interesses", meusInteresses);

        return "pages/dashboardAluno";
    }

    @GetMapping("/turmas")
    public String turmas(Model model,
            @RequestParam(required = false) String buscaDisciplina,
            @RequestParam(required = false) String turno,
            @RequestParam(required = false) String buscaProfessor) {

        List<TurmaModel> turmasEncontradas;

        
        if (buscaDisciplina != null && !buscaDisciplina.trim().isEmpty()) {
            turmasEncontradas = turmaService.filtrarPorDisciplina(buscaDisciplina);

        } else if (buscaProfessor != null && !buscaProfessor.trim().isEmpty()) {
            turmasEncontradas = turmaService.filtrarPorProfessor(buscaProfessor);

        } else if (turno != null && !turno.trim().isEmpty()) {
            turmasEncontradas = turmaService.filtrarPorHorario(turno);

        } else {
            turmasEncontradas = turmaService.listarTodas();
        }

        model.addAttribute("turmas", turmasEncontradas);
        model.addAttribute("buscaDisciplina", buscaDisciplina);
        model.addAttribute("turnoSelecionado", turno);
        model.addAttribute("buscaProfessor", buscaProfessor);

        return "pages/turmas";
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        return "pages/ranking";
    }

    @GetMapping("/configuracoes")
    public String configuracoes(HttpSession session, Model model) {
        PessoaModel usuarioLogado = (PessoaModel) session.getAttribute("usuarioLogado");

        // Se ninguém estiver logado, manda de volta para a página inicial
        if (usuarioLogado == null) {
            return "redirect:/";
        }

        if (usuarioLogado.getIsAluno()) {
            AlunoModel aluno = (AlunoModel) usuarioLogado;
            model.addAttribute("aluno", aluno);
        }
        return "pages/configuracoes";
    }

    @GetMapping("/logout")
    public String fazerLogout(HttpSession session) {
        session.invalidate();

        return "redirect:/sam";
    }
}
