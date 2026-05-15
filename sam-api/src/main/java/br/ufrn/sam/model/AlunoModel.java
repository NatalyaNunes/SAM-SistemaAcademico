package br.ufrn.sam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    public AlunoModel() {}

    public AlunoModel(String login, String senha, Boolean isAluno,
                      String matricula, String nome,
                      Double iea, Double ira,
                      Integer periodo, Boolean isConcluinte) {
        super(login, senha, isAluno);
        this.matricula = matricula;
        this.nome = nome;
        this.iea = iea;
        this.ira = ira;
        this.periodo = periodo;
        this.isConcluinte = isConcluinte;
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
}
