package br.ufrn.sam.controller;

import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.model.TurmaComOcupacaoDTO;
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
    
    // GET /api/turmas/com-ocupacao → lista todas as turmas com quantidade de interessados e percentual de ocupação
    @GetMapping("/com-ocupacao")
    public ResponseEntity<List<TurmaComOcupacaoDTO>> listarComOcupacao() {
        return ResponseEntity.ok(turmaService.listarTodasComOcupacao());
    }
    
    // GET /api/turmas/ociosas → lista turmas com ocupação <= 25%
    @GetMapping("/ociosas")
    public ResponseEntity<List<TurmaComOcupacaoDTO>> listarOciosas() {
        return ResponseEntity.ok(turmaService.listarTurmasOciosas());
    }

    // GET /api/turmas/ociosas/contagem → quantidade de turmas com ocupação <= 25%
    @GetMapping("/ociosas/contagem")
    public ResponseEntity<Long> contarOciosas() {
        return ResponseEntity.ok(turmaService.contarTurmasOciosas());
    }
}
