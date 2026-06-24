package br.ufrn.sam.service;

import br.ufrn.sam.model.TurmaModel;
import br.ufrn.sam.model.InteresseModel;
import br.ufrn.sam.model.TurmaComOcupacaoDTO;
import br.ufrn.sam.repository.JpaTurmaRepository;
import br.ufrn.sam.repository.JpaInteresseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    private final JpaTurmaRepository turmaRepository;
    private final JpaInteresseRepository interesseRepository;

    public TurmaService(JpaTurmaRepository turmaRepository, JpaInteresseRepository interesseRepository) {
        this.turmaRepository = turmaRepository;
        this.interesseRepository = interesseRepository;
    }

    public TurmaModel cadastrar(TurmaModel turma) {
        if (turmaRepository.findByDisciplinaAndAnoAndSemestreAndNumero(turma.getDisciplina(), turma.getAno(),
                turma.getSemestre(), turma.getNumero()).isPresent()) {
            throw new IllegalArgumentException("Código de turma já cadastrada no mesmo semestre e ano!");
        }
        return turmaRepository.save(turma);
    }

    // Método para buscar turmas que estão associadas a disciplinas com codigo x

    /*
     * public TurmaModel buscarPorCodigo(String codigo) {
     * return turmaRepository.findByCodigo(codigo)
     * .orElseThrow(() -> new NoSuchElementException("Turma não encontrada: " +
     * codigo));
     * }
     */

    public TurmaModel findById(Integer idTurma) {
        return turmaRepository.findById(idTurma)
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrada pelo ID: " + idTurma));
    }

    public List<TurmaModel> listarTodas() {
        return turmaRepository.findAll();
    }

    public List<TurmaModel> listarTurmasPorInteresseAluno(String matricula) {
        // 1. Busca todos os registros de interesse daquele aluno
        List<InteresseModel> interesses = interesseRepository.findByAlunoMatricula(matricula);

        // 2. Extrai e retorna apenas os objetos TurmaModel de dentro dos interesses
        return interesses.stream()
                .map(InteresseModel::getTurma)
                .collect(Collectors.toList());
    }

    public List<String> decomporHorario(String horario) {
        List<String> blocos = new ArrayList<>();
        if (horario == null || horario.trim().isEmpty()) {
            return blocos;
        }

        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("(\\d+)([MTNmtn])(\\d+)").matcher(horario);

        while (matcher.find()) {
            String dias = matcher.group(1);
            String turno = matcher.group(2).toUpperCase();
            String horas = matcher.group(3);

            for (char dia : dias.toCharArray()) {
                for (char hora : horas.toCharArray()) {
                    blocos.add("" + dia + turno + hora);
                }
            }
        }
        return blocos;
    }

    public Set<Integer> verificarConflitos(List<TurmaModel> turmas) {
        Set<Integer> turmasComConflito = new HashSet<>();
        java.util.Map<String, Integer> mapaBlocos = new java.util.HashMap<>();

        for (TurmaModel turma : turmas) {
            if (turma.getIdTurma() == null)
                continue;

            List<String> blocosDaTurma = decomporHorario(turma.getHorario());

            for (String bloco : blocosDaTurma) {
                if (mapaBlocos.containsKey(bloco)) {
                    turmasComConflito.add(turma.getIdTurma());
                    turmasComConflito.add(mapaBlocos.get(bloco));
                } else {
                    mapaBlocos.put(bloco, turma.getIdTurma());
                }
            }
        }
        return turmasComConflito;
    }

    public TurmaComOcupacaoDTO calcularOcupacao(TurmaModel turma) {
        int quantidadeInteressados = interesseRepository.findByTurmaIdTurma(turma.getIdTurma()).size();

        int capacidade;
        if (turma.getCapacidade() != null) {
            capacidade = turma.getCapacidade();
        } else {
            capacidade = 0;
        }

        int percentual = 0;
        if (capacidade > 0) {
            percentual = (int) Math.round((quantidadeInteressados * 100.0) / capacidade);
        }

        return new TurmaComOcupacaoDTO(turma, quantidadeInteressados, percentual);
    }

    public List<TurmaComOcupacaoDTO> listarTodasComOcupacao() {
        return turmaRepository.findAll()
                .stream()
                .map(this::calcularOcupacao)
                .collect(Collectors.toList());
    }

    public List<TurmaComOcupacaoDTO> listarTurmasOciosas() {
        return turmaRepository.findAll()
                .stream()
                .map(this::calcularOcupacao)
                .filter(dto -> dto.getPercentualOcupacao() <= 25)
                .collect(Collectors.toList());
    }

    public long contarTurmasOciosas() {
        return listarTurmasOciosas().size();
    }

    public List<TurmaModel> filtrarPorHorario(String horario) {
        if (horario == null || horario.trim().isEmpty()) {
            return turmaRepository.findAll();
        }
        return turmaRepository.findByHorarioContaining(horario);
    }

    public List<TurmaModel> filtrarPorProfessor(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return turmaRepository.findAll();
        }
        return turmaRepository.findByProfessorNomeContainingIgnoreCase(nome);
    }

    public List<TurmaModel> filtrarPorDisciplina(String busca) {
        if (busca == null || busca.trim().isEmpty()) {
            return turmaRepository.findAll();
        }
        return turmaRepository.findByDisciplinaCodigoContainingIgnoreCaseOrDisciplinaNomeContainingIgnoreCase(busca,
                busca);
    }
}
