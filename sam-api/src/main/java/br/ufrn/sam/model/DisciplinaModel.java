package br.ufrn.sam.model;

public class DisciplinaModel {

	    private int idDisciplina;
	    private String codigo;
	    private String nome;
	    private int chTotal;

	    public DisciplinaModel() {}

	    public DisciplinaModel(Integer idDisciplina, String codigo, String nome, Integer chTotal) {
	        this.idDisciplina = idDisciplina;
	        this.codigo = codigo;
	        this.nome = nome;
	        this.chTotal = chTotal;
	    }

	    public int getIdDisciplina() { return idDisciplina; }
	    public void setIdDisciplina(int idDisciplina) { this.idDisciplina = idDisciplina; }

	    public String getCodigo() { return codigo; }
	    public void setCodigo(String codigo) { this.codigo = codigo; }

	    public String getNome() { return nome; }
	    public void setNome(String nome) { this.nome = nome; }

	    public int getChTotal() { return chTotal; }
	    public void setChTotal(Integer chTotal) { this.chTotal = chTotal; }
}

