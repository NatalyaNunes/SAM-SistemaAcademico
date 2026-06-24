package br.ufrn.sam.service;

import br.ufrn.sam.model.ProfessorModel;
import br.ufrn.sam.repository.JpaProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProfessorService {

    private final JpaProfessorRepository professorRepository;

    public ProfessorService(JpaProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public ProfessorModel cadastrar(ProfessorModel professor) {
        if (professorRepository.findBySiape(professor.getSiape()).isPresent()) {
            throw new IllegalArgumentException("SIAPE já cadastrado: " + professor.getSiape());
        }
        return professorRepository.save(professor);
    }

    public ProfessorModel buscarPorSiape(String siape) {
        return professorRepository.findBySiape(siape)
                .orElseThrow(() -> new NoSuchElementException("Professor não encontrado: " + siape));
    }

    public List<ProfessorModel> listarTodos() {
        return professorRepository.findAll();
    }

    public ProfessorModel atualizar(Integer idPessoa, ProfessorModel professorAtualizado) {
        ProfessorModel professorExistente = professorRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com o ID: " + idPessoa));

        professorExistente.setSiape(professorAtualizado.getSiape());
        professorExistente.setDepartamento(professorAtualizado.getDepartamento());
        professorExistente.setTitulacao(professorAtualizado.getTitulacao());
        professorExistente.setNome(professorAtualizado.getNome());
        professorExistente.setLogin(professorAtualizado.getLogin());

        return professorRepository.save(professorExistente);
    }
}
