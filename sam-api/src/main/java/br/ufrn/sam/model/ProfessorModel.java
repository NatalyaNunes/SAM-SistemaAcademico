package br.ufrn.sam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class ProfessorModel extends PessoaModel {

    private int idProfessor;

    @Column(nullable = false, unique = true, length = 15)
    private String siape;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String departamento;

    @Column(nullable = false, length = 50)
    private String titulacao;

    public ProfessorModel() {}

    public ProfessorModel(String login, String senha, boolean isAluno,
                          String siape, String nome,
                          String departamento, String titulacao) {
        super(login, senha, isAluno);
        this.siape = siape;
        this.nome = nome;
        this.departamento = departamento;
        this.titulacao = titulacao;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
}
