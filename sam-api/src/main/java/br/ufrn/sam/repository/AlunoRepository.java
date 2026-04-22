package br.ufrn.sam.repository;

import br.ufrn.sam.model.AlunoModel;
import java.util.List;
import java.util.Optional;

public interface AlunoRepository {
    AlunoModel save(AlunoModel aluno);
    Optional<AlunoModel> findByMatricula(String matricula); //busca um aluno pela matrícula
    List<AlunoModel> findAll(); //retorna os alunos cadastrados
}