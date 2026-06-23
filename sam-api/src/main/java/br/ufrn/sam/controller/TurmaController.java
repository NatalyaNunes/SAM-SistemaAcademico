package br.ufrn.sam.controller;

import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    // POST /api/turmas → cadastra uma nova turma
    @PostMapping
    public ResponseEntity<TurmaModel> cadastrar(@RequestBody TurmaModel turma) {
        TurmaModel salva = turmaService.cadastrar(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva); // retorna 201
    }

    // GET /api/turmas/{idTurma} → busca uma turma pelo ID
    @GetMapping("/{idTurma}")
    public ResponseEntity<TurmaModel> buscar(@PathVariable Integer idTurma) {
        return ResponseEntity.ok(turmaService.findById(idTurma)); // retorna 200
    }

    // GET /api/turmas → lista todas as turmas
    @GetMapping
    public ResponseEntity<List<TurmaModel>> listar() {
        return ResponseEntity.ok(turmaService.listarTodas());
    }
}
