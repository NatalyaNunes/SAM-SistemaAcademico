package br.ufrn.sam.service;

import br.ufrn.sam.model.AlunoModel;
import br.ufrn.sam.model.InteresseModel;
import br.ufrn.sam.model.NivelPrioridade;

import java.util.Comparator;

/**
 * Ordena InteresseModel de uma mesma turma conforme a Legenda de Prioridades:
 *
 * 1) §1º - Prioridade absoluta: aluno com ingresso por vestibular no primeiro
 *    período letivo tem prioridade máxima para componentes do primeiro nível
 *    da estrutura curricular (disciplina.primeiroNivel == true).
 * 2) Nível de prioridade (I, II, III, III*, IV, V), conforme ordem do enum
 *    NivelPrioridade (menor ordinal = maior prioridade).
 * 3) §3º - Desempate final: maior IEA vence.
 */
public class InteresseRankingComparator implements Comparator<InteresseModel> {

    @Override
    public int compare(InteresseModel i1, InteresseModel i2) {
        AlunoModel a1 = i1.getAluno();
        AlunoModel a2 = i2.getAluno();

        // 1) Prioridade absoluta do §1º
        boolean prioridadeAbsoluta1 = temPrioridadeAbsoluta(i1);
        boolean prioridadeAbsoluta2 = temPrioridadeAbsoluta(i2);
        if (prioridadeAbsoluta1 != prioridadeAbsoluta2) {
            // quem tem prioridade absoluta (true) vem primeiro
            return prioridadeAbsoluta1 ? -1 : 1;
        }

        // 2) Nível de prioridade (menor ordinal = maior prioridade)
        int comparacaoNivel = compararNivel(a1.getNivelPrioridade(), a2.getNivelPrioridade());
        if (comparacaoNivel != 0) {
            return comparacaoNivel;
        }

        // 3) Desempate final do §3º: maior IEA vence
        double iea1 = a1.getIea() != null ? a1.getIea() : 0.0;
        double iea2 = a2.getIea() != null ? a2.getIea() : 0.0;
        return Double.compare(iea2, iea1); // maior IEA vence -> ordem decrescente
    }

    private boolean temPrioridadeAbsoluta(InteresseModel interesse) {
        AlunoModel aluno = interesse.getAluno();
        boolean disciplinaPrimeiroNivel = interesse.getTurma() != null
                && interesse.getTurma().getDisciplina() != null
                && Boolean.TRUE.equals(interesse.getTurma().getDisciplina().getPrimeiroNivel());

        boolean alunoVestibularPrimeiroPeriodo = Boolean.TRUE.equals(aluno.getIngressoVestibularPrimeiroPeriodo());

        return disciplinaPrimeiroNivel && alunoVestibularPrimeiroPeriodo;
    }

    private int compararNivel(NivelPrioridade nivel1, NivelPrioridade nivel2) {
        if (nivel1 == null && nivel2 == null) return 0;
        if (nivel1 == null) return 1;  // nível ausente vai para o final
        if (nivel2 == null) return -1;
        return Integer.compare(nivel1.ordinal(), nivel2.ordinal());
    }
}