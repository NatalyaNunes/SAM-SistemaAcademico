package br.ufrn.sam.model;

import br.ufrn.sam.model.TurmaModel;

public class TurmaComOcupacaoDTO {

    private TurmaModel turma;
    private int quantidadeInteressados;
    private int percentualOcupacao;

    public TurmaComOcupacaoDTO() {}

    public TurmaComOcupacaoDTO(TurmaModel turma, int quantidadeInteressados, int percentualOcupacao) {
        this.turma = turma;
        this.quantidadeInteressados = quantidadeInteressados;
        this.percentualOcupacao = percentualOcupacao;
    }

    public TurmaModel getTurma() { return turma; }
    public void setTurma(TurmaModel turma) { this.turma = turma; }

    public int getQuantidadeInteressados() { return quantidadeInteressados; }
    public void setQuantidadeInteressados(int quantidadeInteressados) {
        this.quantidadeInteressados = quantidadeInteressados;
    }

    public int getPercentualOcupacao() { return percentualOcupacao; }
    public void setPercentualOcupacao(int percentualOcupacao) { this.percentualOcupacao = percentualOcupacao; }
}
