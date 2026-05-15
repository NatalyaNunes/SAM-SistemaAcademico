package br.ufrn.sam.service;

import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.repository.JpaAlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AlunoService {

    private final JpaAlunoRepository alunoRepository;

    public AlunoService(JpaAlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoModel cadastrar(AlunoModel aluno) {
        if (alunoRepository.findByMatricula(aluno.getMatricula()).isPresent()) {
            throw new IllegalArgumentException("Matrícula já cadastrada: " + aluno.getMatricula());
        }
        return alunoRepository.save(aluno);
    }

    public AlunoModel buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado: " + matricula));
    }

    public List<AlunoModel> listarTodos() {
        return alunoRepository.findAll();
    }
}