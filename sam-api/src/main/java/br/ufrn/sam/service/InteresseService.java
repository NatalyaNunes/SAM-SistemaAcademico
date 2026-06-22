package br.ufrn.sam.service;

import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.model.InteresseModel;
import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.repository.JpaAlunoRepository;
import br.ufrn.sam.repository.JpaInteresseRepository;
import br.ufrn.sam.repository.JpaTurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InteresseService {

    private final JpaInteresseRepository interesseRepository;
    private final JpaAlunoRepository alunoRepository;
    private final JpaTurmaRepository turmaRepository;

    public InteresseService(JpaInteresseRepository interesseRepository,
                            JpaAlunoRepository alunoRepository,
                            JpaTurmaRepository turmaRepository) {
        this.interesseRepository = interesseRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    public InteresseModel cadastrar(String matricula, String codigoTurma) {
        // busca o aluno pela matrícula
        AlunoModel aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado: " + matricula));

        // busca a turma pelo código
        TurmaModel turma = turmaRepository.findByCodigo(codigoTurma)
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrada: " + codigoTurma));

        // verifica se já existe interesse do aluno nessa turma
        List<InteresseModel> interesses = interesseRepository.findByAlunoIdAluno(aluno.getIdAluno());
        for (InteresseModel i : interesses) {
            if (i.getTurma().getCodigo().equals(codigoTurma)) {
                throw new IllegalArgumentException("Aluno já manifestou interesse nessa turma!");
            }
        }

        InteresseModel interesse = new InteresseModel(
                java.time.LocalDate.now().toString(),
                null,
                aluno,
                turma
        );

        return interesseRepository.save(interesse);
    }

    public List<InteresseModel> listarPorAluno(String matricula) {
        AlunoModel aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado: " + matricula));
        return interesseRepository.findByAlunoIdAluno(aluno.getIdAluno());
    }

    public List<InteresseModel> listarPorTurma(String codigoTurma) {
        TurmaModel turma = turmaRepository.findByCodigo(codigoTurma)
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrada: " + codigoTurma));

        List<InteresseModel> interesses = interesseRepository.findByTurmaIdTurma(turma.getIdTurma());

        interesses.sort(new InteresseRankingComparator());

        return interesses;
    }
    
    public List<InteresseModel> listarTodos() {
        return interesseRepository.findAll();
    }
}