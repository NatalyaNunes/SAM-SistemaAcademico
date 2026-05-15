package br.ufrn.sam.service;

import br.ufrn.sam.model.DisciplinaModel;
import br.ufrn.sam.repository.JpaDisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DisciplinaService {

    private final JpaDisciplinaRepository disciplinaRepository;

    public DisciplinaService(JpaDisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public DisciplinaModel cadastrar(DisciplinaModel disciplina) {
        if (disciplinaRepository.findByCodigo(disciplina.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Código já cadastrado: " + disciplina.getCodigo());
        }
        return disciplinaRepository.save(disciplina);
    }

    public DisciplinaModel buscarPorCodigo(String codigo) {
        return disciplinaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new NoSuchElementException("Disciplina não encontrada: " + codigo));
    }

    public List<DisciplinaModel> listarTodas() {
        return disciplinaRepository.findAll();
    }
}