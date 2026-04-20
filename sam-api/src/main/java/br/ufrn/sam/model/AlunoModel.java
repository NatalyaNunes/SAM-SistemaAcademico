package br.ufrn.sam.model;

public class AlunoModel {
	private String nome;
	private String matricula;
	private String curso;
	private Double ira;
	
	public AlunoModel() {}
	
	public AlunoModel(String nome, String matricula, String curso, Double ira) {
		this.nome=nome;
		this.matricula=matricula;
		this.curso=curso;
		this.ira=ira;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	
	 public String getMatricula() {
	     return matricula;
	 }

	 public void setMatricula(String matricula) {
	     this.matricula = matricula;
	 }

    public String getCurso() {
	     return curso;
    }
    public void setCurso(String curso) {
	     this.curso = curso;
	}

    public Double getIra() {
	     return ira;
	}
    
    public void setIra(Double ira) {
	    this.ira = ira;
    }
}
