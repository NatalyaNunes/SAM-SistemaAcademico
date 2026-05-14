package br.ufrn.sam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
// JOINED = cada classe filha tem sua própria tabela no banco
// ligadas por chave estrangeira com a tabela pessoas

public class PessoaModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // IDENTITY = o banco gera o id automaticamente (auto increment)
	private int idPessoa;
	
	@Column(nullable = false)
    private String login;
	
	@Column(nullable = false)
    private String senha;
	
	@Column(nullable = false)
    private boolean isAluno;
    
    public PessoaModel() {}

    public PessoaModel(String login, String senha, boolean isAluno) {
        this.login = login;
        this.senha = senha;
        this.isAluno = isAluno;
    }

    public int getIdPessoa() { return idPessoa; }
    public void setIdPessoa(int idPessoa) { this.idPessoa = idPessoa; }
    
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public boolean getIsAluno() { return isAluno; }
    public void setIsAluno(boolean isAluno) { this.isAluno = isAluno; }
}
