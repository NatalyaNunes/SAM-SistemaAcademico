package br.ufrn.sam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "interesse",
    uniqueConstraints = @UniqueConstraint(columnNames = {"aluno_id", "turma_id"})
    // garante que um aluno não pode se interessar pela mesma turma duas vezes
)
public class InteresseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInteresse;

    @Column(nullable = false)
    private String dataRegistro;

    @Column
    private Integer posicaoLista;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoModel aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private TurmaModel turma;

    public InteresseModel() {}

    public InteresseModel(String dataRegistro, Integer posicaoLista, AlunoModel aluno, TurmaModel turma) {
        this.dataRegistro = dataRegistro;
        this.posicaoLista = posicaoLista;
        this.aluno = aluno;
        this.turma = turma;
    }

    public Integer getIdInteresse() { return idInteresse; }
    public void setIdInteresse(Integer idInteresse) { this.idInteresse = idInteresse; }

    public String getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }

    public Integer getPosicaoLista() { return posicaoLista; }
    public void setPosicaoLista(Integer posicaoLista) { this.posicaoLista = posicaoLista; }

    public AlunoModel getAluno() { return aluno; }
    public void setAluno(AlunoModel aluno) { this.aluno = aluno; }

    public TurmaModel getTurma() { return turma; }
    public void setTurma(TurmaModel turma) { this.turma = turma; }
}