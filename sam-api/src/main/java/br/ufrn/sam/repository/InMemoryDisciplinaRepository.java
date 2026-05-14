package br.ufrn.sam.repository;

import br.ufrn.sam.model.DisciplinaModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryDisciplinaRepository implements DisciplinaRepository {

    private final Map<String, DisciplinaModel> db = new HashMap<>();

    @Override
    public DisciplinaModel save(DisciplinaModel disciplina) {
        db.put(disciplina.getCodigo(), disciplina); //insere a disciplina no HashMap usando o código como chave
        return disciplina;
    }

    @Override
    public Optional<DisciplinaModel> findByCodigo(String codigo) {
        return Optional.ofNullable(db.get(codigo));  //Procura a disciplina pelo código
    }

    @Override
    public List<DisciplinaModel> findAll() {
        return new ArrayList<>(db.values());  //Retorna uma lista com todos os valores do HashMap (todos as disciplinas)
    }

}

