package br.ufrn.sam.repository;

import br.ufrn.sam.model.DisciplinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDisciplinaRepository extends JpaRepository<DisciplinaModel, Integer> {
    Optional<DisciplinaModel> findByCodigo(String codigo);
}