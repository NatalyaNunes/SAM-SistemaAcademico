package br.ufrn.sam.model;

public class PessoaModel {

	private int idPessoa;
    private String login;
    private String senha;
    private String isAluno;
    
    public PessoaModel() {}

    public PessoaModel(int idPessoa, String login, String senha, String isAluno) {
    	this.idPessoa = idPessoa;
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
    
    public String getIsAluno() { return isAluno; }
    public void setIsAluno(String isAluno) { this.isAluno = isAluno; }
}
