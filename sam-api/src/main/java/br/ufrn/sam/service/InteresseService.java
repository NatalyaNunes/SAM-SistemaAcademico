package br.ufrn.sam.service;

import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.model.InteresseModel;
import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.repository.JpaAlunoRepository;
import br.ufrn.sam.repository.JpaInteresseRepository;
import br.ufrn.sam.repository.JpaTurmaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class InteresseService {

    private final JpaInteresseRepository interesseRepository;
    private final JpaAlunoRepository alunoRepository;
    private final JpaTurmaRepository turmaRepository;
    private final TurmaService turmaService;

    public InteresseService(JpaInteresseRepository interesseRepository,
            JpaAlunoRepository alunoRepository,
            JpaTurmaRepository turmaRepository,
            TurmaService turmaService) {
        this.interesseRepository = interesseRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.turmaService = turmaService;
    }

    public InteresseModel cadastrar(String matricula, Integer idTurma) {
        // busca o aluno pela matrícula
        AlunoModel aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado: " + matricula));

        // busca a turma pelo código
        TurmaModel turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrada: " + idTurma));

        // verifica se já existe interesse do aluno nessa turma
        List<InteresseModel> interesses = interesseRepository.findByAlunoIdAluno(aluno.getIdAluno());
        for (InteresseModel i : interesses) {
            if (i.getTurma().getIdTurma().equals(idTurma)) {
                throw new IllegalArgumentException("Aluno já manifestou interesse nessa turma!");
            }
        }

        InteresseModel interesse = new InteresseModel(
                java.time.LocalDate.now().toString(),
                null,
                aluno,
                turma);

        return interesseRepository.save(interesse);
    }

    public List<InteresseModel> listarPorAluno(String matricula) {
        AlunoModel aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado: " + matricula));
        return interesseRepository.findByAlunoIdAluno(aluno.getIdAluno());
    }

    public List<InteresseModel> listarPorTurma(Integer idTurma) {
        TurmaModel turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrada: " + idTurma));

        List<InteresseModel> interesses = interesseRepository.findByTurmaIdTurma(turma.getIdTurma());

        interesses.sort(new InteresseRankingComparator());

        return interesses;
    }

    public List<InteresseModel> listarTodos() {
        return interesseRepository.findAll();
    }

    public boolean simularConflitoGrade(String matricula, String codigoTurmaNova) {
        List<InteresseModel> interessesAtuais = listarPorAluno(matricula);

        List<TurmaModel> turmasParaChecar = new ArrayList<>();
        for (InteresseModel interesse : interessesAtuais) {
            turmasParaChecar.add(interesse.getTurma());
        }

        TurmaModel turmaNova = turmaRepository.findById(Integer.parseInt(codigoTurmaNova))
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrada: " + codigoTurmaNova));
        turmasParaChecar.add(turmaNova);

        Set<Integer> idsEmConflito = turmaService.verificarConflitos(turmasParaChecar);

        return idsEmConflito.contains(turmaNova.getIdTurma());
    }
}