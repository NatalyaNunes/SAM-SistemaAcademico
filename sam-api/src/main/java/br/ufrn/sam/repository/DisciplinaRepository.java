package br.ufrn.sam.repository;

import br.ufrn.sam.model.DisciplinaModel;
import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository {
	DisciplinaModel save(DisciplinaModel disciplina);
	Optional<DisciplinaModel> findByCodigo(int codigo); //busca umsa disciplina pelo código
	List<DisciplinaModel> findAll(); //retorna as disciplinas cadastradas
}
