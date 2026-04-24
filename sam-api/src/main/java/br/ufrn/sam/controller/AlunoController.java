package br.ufrn.sam.controller;

import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}