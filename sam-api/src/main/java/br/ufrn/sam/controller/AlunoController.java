package br.ufrn.sam.controller;

import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.model.PessoaModel;
import br.ufrn.sam.service.AlunoService;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;

   
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // POST /api/alunos → cadastra um novo aluno
    @PostMapping
    public ResponseEntity<AlunoModel> cadastrar(@RequestBody AlunoModel aluno) {
        AlunoModel salvo = alunoService.cadastrar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo); // retorna 201
    }

    // GET /api/alunos/{matricula} → busca um aluno pela matrícula
    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoModel> buscar(@PathVariable String matricula) {
        return ResponseEntity.ok(alunoService.buscarPorMatricula(matricula)); // retorna 200
    }

    // GET /api/alunos → lista todos os alunos
    @GetMapping
    public ResponseEntity<List<AlunoModel>> listar() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @PutMapping("/perfil")
    public ResponseEntity<?> atualizarPerfil(@RequestBody Map<String, Object> dados, HttpSession session) {
        PessoaModel usuarioLogado = (PessoaModel) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !usuarioLogado.getIsAluno()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autorizado ou sessão expirada.");
        }

        try {
            AlunoModel alunoAtual = (AlunoModel) usuarioLogado;

            alunoAtual.setNome((String) dados.get("nome"));
            alunoAtual.setLogin((String) dados.get("login"));
            alunoAtual.setPeriodo(Integer.parseInt(dados.get("periodo").toString()));
            alunoAtual.setIra(Double.parseDouble(dados.get("ira").toString()));
            alunoAtual.setIea(Double.parseDouble(dados.get("iea").toString()));
            alunoAtual.setIsConcluinte((Boolean) dados.get("isConcluinte"));

            AlunoModel alunoSalvo = alunoService.cadastrar(alunoAtual);
            session.setAttribute("usuarioLogado", alunoSalvo);

            return ResponseEntity.ok().body("{\"mensagem\": \"Perfil atualizado com sucesso\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a atualização.");
        }
    }
}