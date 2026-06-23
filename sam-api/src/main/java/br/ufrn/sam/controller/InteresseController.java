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

    // POST /api/interesses/{matricula}/{idTurma} → registra interesse
    @PostMapping("/{matricula}/{idTurma}")
    public ResponseEntity<InteresseModel> cadastrar(
            @PathVariable String matricula,
            @PathVariable Integer idTurma) {
        InteresseModel salvo = interesseService.cadastrar(matricula, idTurma);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /api/interesses/aluno/{matricula} → lista interesses de um aluno
    @GetMapping("/aluno/{matricula}")
    public ResponseEntity<List<InteresseModel>> listarPorAluno(@PathVariable String matricula) {
        return ResponseEntity.ok(interesseService.listarPorAluno(matricula));
    }

    // GET /api/interesses/turma/{idTurma} → lista interessados em uma turma
    @GetMapping("/turma/{idTurma}")
    public ResponseEntity<List<InteresseModel>> listarPorTurma(@PathVariable Integer idTurma) {
        return ResponseEntity.ok(interesseService.listarPorTurma(idTurma));
    }

    // GET /api/interesses → lista todos
    @GetMapping
    public ResponseEntity<List<InteresseModel>> listarTodos() {
        return ResponseEntity.ok(interesseService.listarTodos());
    }

    @GetMapping("/aluno/{matricula}/simular-conflito/{idTurma}")
    public ResponseEntity<Map<String, Boolean>> checarConflito(
            @PathVariable String matricula,
            @PathVariable Integer idTurma) {

        boolean temConflito = interesseService.simularConflitoGrade(matricula, idTurma.toString());

        return ResponseEntity.ok(java.util.Map.of("conflito", temConflito));
    }
}