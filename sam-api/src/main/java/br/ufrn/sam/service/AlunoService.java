package br.ufrn.sam.service;

import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    // Spring coloca o repositório automaticamente aqui
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoModel cadastrar(AlunoModel aluno) {
        // verifica se já existe um aluno com essa matrícula
        if (alunoRepository.findByMatricula(aluno.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já cadastrada: " + aluno.getMatricula());
        }
        return alunoRepository.save(aluno);
    }

    public AlunoModel buscarPorMatricula(String matricula) {
        // lança exceção automaticamente se não encontrar
        return alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado: " + matricula));
    }

    public List<AlunoModel> listarTodos() {
        return alunoRepository.findAll();
    }
}