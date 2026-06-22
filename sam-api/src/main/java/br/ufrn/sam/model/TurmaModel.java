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
@Table(name = "turma", uniqueConstraints = @UniqueConstraint(columnNames = { "id_disciplina", "ano", "semestre",
        "numero" }))
public class TurmaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTurma;

    @Column(nullable = false)
    private Integer numero; // Substituiu o 'codigo' e o 'nome' (ex: 1, 2, 3...)

    @Column(nullable = false, length = 30)
    private String horario;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false)
    private DisciplinaModel disciplina;

    @ManyToOne
    @JoinColumn(name = "id_professor", nullable = false)
    private ProfessorModel professor;

    @Column(nullable = false)
    private Integer semestre;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private Integer capacidade;

    public TurmaModel() {
    }

    public TurmaModel(Integer numero, String horario, DisciplinaModel disciplina,
            ProfessorModel professor, Integer semestre, Integer ano,
            Integer capacidade) {
        this.numero = numero;
        this.horario = horario;
        this.disciplina = disciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.ano = ano;
        this.capacidade = capacidade;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public DisciplinaModel getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaModel disciplina) {
        this.disciplina = disciplina;
    }

    public ProfessorModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
}