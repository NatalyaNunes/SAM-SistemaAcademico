package br.ufrn.sam.repository;

import br.ufrn.sam.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAlunoRepository extends JpaRepository<AlunoModel, Integer> {
    Optional<AlunoModel> findByMatricula(String matricula);
}