package br.ufrn.sam.repository;

import br.ufrn.sam.model.AlunoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryAlunoRepository implements AlunoRepository {

    private final Map<String, AlunoModel> db = new HashMap<>();

    @Override
    public AlunoModel save(AlunoModel aluno) {
        db.put(aluno.getMatricula(), aluno); //insere o aluno no HashMap usando a matrícula como chave
        return aluno;
    }

    @Override
    public Optional<AlunoModel> findByMatricula(String matricula) {
        return Optional.ofNullable(db.get(matricula));  //Procura o aluno pela matrícula
    }

    @Override
    public List<AlunoModel> findAll() {
        return new ArrayList<>(db.values());  //Retorna uma lista com todos os valores do HashMap (todos os alunos)
    }
}