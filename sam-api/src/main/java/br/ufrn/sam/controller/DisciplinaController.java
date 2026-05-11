package br.ufrn.sam.controller;

import br.ufrn.sam.model.DisciplinaModel;
import br.ufrn.sam.service.DisciplinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

   
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    // POST /api/disciplinas → cadastra uma nova disciplina
    @PostMapping
    public ResponseEntity<DisciplinaModel> cadastrar(@RequestBody DisciplinaModel disciplina) {
        DisciplinaModel salvo = disciplinaService.cadastrar(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo); 
    }

    // GET /api/disciplinas/{codigo} → busca uma disciplina pelo codigo
    @GetMapping("/{codigo}")
    public ResponseEntity<DisciplinaModel> buscar(@PathVariable String codigo) {
        return ResponseEntity.ok(disciplinaService.buscarPorCodigo(codigo)); 
    }

    // GET /api/disciplinas → lista todas as disciplinas
    @GetMapping
    public ResponseEntity<List<DisciplinaModel>> listar() {
        return ResponseEntity.ok(disciplinaService.listarTodos());
    }
}