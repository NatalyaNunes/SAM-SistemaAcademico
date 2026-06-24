package br.ufrn.sam.repository;

import br.ufrn.sam.model.InteresseModel;
import br.ufrn.sam.model.DisciplinaDemandaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaInteresseRepository extends JpaRepository<InteresseModel, Integer> {

    List<InteresseModel> findByAlunoMatricula(String matricula);

    List<InteresseModel> findByAlunoIdAluno(Integer idAluno);

    List<InteresseModel> findByTurmaIdTurma(Integer idTurma);

    @Query("SELECT new br.ufrn.sam.model.DisciplinaDemandaDTO(d.codigo, d.nome, COUNT(i)) " +
            "FROM InteresseModel i " +
            "JOIN i.turma t " +
            "JOIN t.disciplina d " +
            "GROUP BY d.codigo, d.nome " +
            "ORDER BY COUNT(i) DESC")
    List<DisciplinaDemandaDTO> findDisciplinasMaisProcuradas();
}