package br.ufrn.sam.model;

public class PessoaModel {

	private int idPessoa;
    private String login;
    private String senha;
    private boolean isAluno;
    
    public PessoaModel() {}

    public PessoaModel(int idPessoa, String login, String senha, boolean isAluno) {
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
    
    public boolean getIsAluno() { return isAluno; }
    public void setIsAluno(boolean isAluno) { this.isAluno = isAluno; }
}
