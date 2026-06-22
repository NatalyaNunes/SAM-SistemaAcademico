package br.ufrn.sam.controller;

import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.service.TurmaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.model.PessoaModel;
import jakarta.servlet.http.HttpSession;

@Controller
public class RenderController {

    private final TurmaService turmaService;

    public RenderController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/dashboardAluno")
    public String dashboardAluno(HttpSession session, Model model) {
        PessoaModel usuarioLogado = (PessoaModel) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !usuarioLogado.getIsAluno()) {
            return "redirect:/"; // Se não tiver logado, manda pro login
        }

        AlunoModel aluno = (AlunoModel) usuarioLogado;

        List<TurmaModel> turmasDoAluno = turmaService.listarTurmasPorInteresseAluno(aluno.getMatricula());

        Set<Integer> idsEmConflito = turmaService.verificarConflitos(turmasDoAluno);

        model.addAttribute("aluno", aluno);
        model.addAttribute("turmasInteresse", turmasDoAluno);
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
