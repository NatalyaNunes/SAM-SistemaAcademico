package br.ufrn.sam.controller;

import br.ufrn.sam.model.InteresseModel;
import br.ufrn.sam.service.InteresseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interesses")
public class InteresseController {

    private final InteresseService interesseService;

    public InteresseController(InteresseService interesseService) {
        this.interesseService = interesseService;
    }

    // POST /api/interesses/{matricula}/{codigoTurma} → registra interesse
    @PostMapping("/{matricula}/{codigoTurma}")
    public ResponseEntity<InteresseModel> cadastrar(
            @PathVariable String matricula,
            @PathVariable String codigoTurma) {
        InteresseModel salvo = interesseService.cadastrar(matricula, codigoTurma);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /api/interesses/aluno/{matricula} → lista interesses de um aluno
    @GetMapping("/aluno/{matricula}")
    public ResponseEntity<List<InteresseModel>> listarPorAluno(@PathVariable String matricula) {
        return ResponseEntity.ok(interesseService.listarPorAluno(matricula));
    }

    // GET /api/interesses/turma/{codigoTurma} → lista interessados em uma turma
    @GetMapping("/turma/{codigoTurma}")
    public ResponseEntity<List<InteresseModel>> listarPorTurma(@PathVariable String codigoTurma) {
        return ResponseEntity.ok(interesseService.listarPorTurma(codigoTurma));
    }

    // GET /api/interesses → lista todos
    @GetMapping
    public ResponseEntity<List<InteresseModel>> listarTodos() {
        return ResponseEntity.ok(interesseService.listarTodos());
    }

    @GetMapping("/aluno/{matricula}/simular-conflito/{codigoTurma}")
    public ResponseEntity<Map<String, Boolean>> checarConflito(
            @PathVariable String matricula,
            @PathVariable String codigoTurma) {

        boolean temConflito = interesseService.simularConflitoGrade(matricula, codigoTurma);

        return ResponseEntity.ok(java.util.Map.of("conflito", temConflito));
    }
}