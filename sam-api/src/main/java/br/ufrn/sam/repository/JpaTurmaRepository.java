package br.ufrn.sam.repository;

import br.ufrn.sam.model.DisciplinaModel;
import br.ufrn.sam.model.TurmaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTurmaRepository extends JpaRepository<TurmaModel, Integer> {
    Optional<TurmaModel> findByDisciplinaAndAnoAndSemestreAndNumero(
        DisciplinaModel disciplina, 
        Integer ano, 
        Integer semestre, 
        Integer numero
    );
    
    List<TurmaModel> findByHorarioContaining(String horario);
    List<TurmaModel> findByProfessorNomeContainingIgnoreCase(String nome);
}
