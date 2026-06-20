package br.ufrn.sam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplina")
public class DisciplinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisciplina;

    @Column(nullable = false, unique = true, length = 7)
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private Integer chTotal;

    public DisciplinaModel() {}

    public DisciplinaModel(String codigo, String nome, Integer chTotal) {
        this.codigo = codigo;
        this.nome = nome;
        this.chTotal = chTotal;
    }

    public Integer getIdDisciplina() { return idDisciplina; }
    public void setIdDisciplina(Integer idDisciplina) { this.idDisciplina = idDisciplina; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getChTotal() { return chTotal; }
    public void setChTotal(Integer chTotal) { this.chTotal = chTotal; }
}
