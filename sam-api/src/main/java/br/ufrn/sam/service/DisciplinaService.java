package br.ufrn.sam.service;

import br.ufrn.sam.model.DisciplinaModel;
import br.ufrn.sam.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    // Spring coloca o repositório automaticamente aqui
    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public DisciplinaModel cadastrar(DisciplinaModel disciplina) {
        // verifica se já existe uma disciplina com esse código
        if (disciplinaRepository.findByCodigo(disciplina.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Código já cadastrado: " + disciplina.getCodigo());
        }
        return disciplinaRepository.save(disciplina);
    }

    public DisciplinaModel buscarPorCodigo(String codigo) {
        // lança exceção automaticamente se não encontrar
        return disciplinaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new NoSuchElementException("Disciplina não encontrada: " + codigo));
    }

    public List<DisciplinaModel> listarTodos() {
        return disciplinaRepository.findAll();
    }
}