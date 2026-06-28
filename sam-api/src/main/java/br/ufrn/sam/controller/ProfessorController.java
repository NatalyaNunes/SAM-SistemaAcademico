package br.ufrn.sam.controller;

import br.ufrn.sam.model.ProfessorModel;
import br.ufrn.sam.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    // POST /api/professores → cadastra um novo professor
    @PostMapping
    public ResponseEntity<ProfessorModel> cadastrar(@RequestBody ProfessorModel professor) {
        ProfessorModel salvo = professorService.cadastrar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo); // retorna 201
    }

    // GET /api/professores/{siape} → busca um professor pelo SIAPE
    @GetMapping("/{siape}")
    public ResponseEntity<ProfessorModel> buscar(@PathVariable String siape) {
        return ResponseEntity.ok(professorService.buscarPorSiape(siape)); // retorna 200
    }

    // GET /api/professores → lista todos os professores
    @GetMapping
    public ResponseEntity<List<ProfessorModel>> listar() {
        return ResponseEntity.ok(professorService.listarTodos());
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<ProfessorModel> atualizar(@PathVariable Integer idPessoa, @RequestBody ProfessorModel professorAtualizado) {
        ProfessorModel salvo = professorService.atualizar(idPessoa, professorAtualizado);
        return ResponseEntity.ok(salvo); 
    }
}
