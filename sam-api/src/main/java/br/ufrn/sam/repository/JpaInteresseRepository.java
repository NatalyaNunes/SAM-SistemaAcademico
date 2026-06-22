package br.ufrn.sam.repository;

import br.ufrn.sam.model.InteresseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaInteresseRepository extends JpaRepository<InteresseModel, Integer> {

    List<InteresseModel> findByAlunoMatricula(String matricula);

    List<InteresseModel> findByAlunoIdAluno(Integer idAluno);

    List<InteresseModel> findByTurmaIdTurma(Integer idTurma);
}