package br.ufrn.sam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aluno")
public class AlunoModel extends PessoaModel {

    private int idAluno;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double iea;

    @Column(nullable = false)
    private Double ira;

    @Column(nullable = false)
    private Integer periodo;

    @Column(nullable = false)
    private Boolean isConcluinte;

    //Campos de Ranking de Prioridade 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelPrioridade nivelPrioridade;

    @Column(nullable = false)
    private Boolean ingressoVestibularPrimeiroPeriodo;

    //Perfil inicial: período de ingresso previsto na matriz curricular do aluno
    @Column
    private Integer perfilInicial;

    //Períodos letivos regulares cursados, excluindo trancamentos/mobilidade 
    @Column
    private Integer periodosRegularesCursados;

    public AlunoModel() {}

    public AlunoModel(String login, String senha, Boolean isAluno,
                      String matricula, String nome,
                      Double iea, Double ira,
                      Integer periodo, Boolean isConcluinte,
                      NivelPrioridade nivelPrioridade,
                      Boolean ingressoVestibularPrimeiroPeriodo,
                      Integer perfilInicial,
                      Integer periodosRegularesCursados) {
        super(login, senha, isAluno);
        this.matricula = matricula;
        this.nome = nome;
        this.iea = iea;
        this.ira = ira;
        this.periodo = periodo;
        this.isConcluinte = isConcluinte;
        this.nivelPrioridade = nivelPrioridade;
        this.ingressoVestibularPrimeiroPeriodo = ingressoVestibularPrimeiroPeriodo;
        this.perfilInicial = perfilInicial;
        this.periodosRegularesCursados = periodosRegularesCursados;
    }

    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getIea() { return iea; }
    public void setIea(Double iea) { this.iea = iea; }

    public Double getIra() { return ira; }
    public void setIra(Double ira) { this.ira = ira; }

    public Integer getPeriodo() { return periodo; }
    public void setPeriodo(Integer periodo) { this.periodo = periodo; }

    public Boolean getIsConcluinte() { return isConcluinte; }
    public void setIsConcluinte(Boolean isConcluinte) { this.isConcluinte = isConcluinte; }

    public NivelPrioridade getNivelPrioridade() { return nivelPrioridade; }
    public void setNivelPrioridade(NivelPrioridade nivelPrioridade) { this.nivelPrioridade = nivelPrioridade; }

    public Boolean getIngressoVestibularPrimeiroPeriodo() { return ingressoVestibularPrimeiroPeriodo; }
    public void setIngressoVestibularPrimeiroPeriodo(Boolean ingressoVestibularPrimeiroPeriodo) {
        this.ingressoVestibularPrimeiroPeriodo = ingressoVestibularPrimeiroPeriodo;
    }

    public Integer getPerfilInicial() { return perfilInicial; }
    public void setPerfilInicial(Integer perfilInicial) { this.perfilInicial = perfilInicial; }

    public Integer getPeriodosRegularesCursados() { return periodosRegularesCursados; }
    public void setPeriodosRegularesCursados(Integer periodosRegularesCursados) {
        this.periodosRegularesCursados = periodosRegularesCursados;
    }

    /**
     * Soma usada no critério de desempate, aplicável aos níveis I, III e IV.
     * Retorna 0 se algum dos valores não estiver informado, para não quebrar o ranking.
     */
    public int getPontuacaoDesempateNivel() {
        int perfil = perfilInicial != null ? perfilInicial : 0;
        int cursados = periodosRegularesCursados != null ? periodosRegularesCursados : 0;
        return perfil + cursados;
    }
}