package br.ufrn.sam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufrn.sam.model.PessoaModel;

@Repository
public interface JpaPessoaRepository extends JpaRepository<PessoaModel, Integer> {
   Optional<PessoaModel> findByLoginAndSenha(String login, String senha);
}
