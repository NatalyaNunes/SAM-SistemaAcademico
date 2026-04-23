package br.ufrn.sam.model;

public class AlunoModel extends PessoaModel {

    private int idAluno;
    private String matricula;
    private String nome;
    private Double iea;
    private Double ira;
    private Integer periodo;
    private boolean isConcluinte;

    public AlunoModel() {}

    public AlunoModel(int idPessoa, String login, String senha, boolean isAluno,
                      int idAluno, String matricula, String nome,
                      Double iea, Double ira, Integer periodo, boolean isConcluinte) {
        super(idPessoa, login, senha, isAluno); // chama o construtor de PessoaModel
        this.idAluno = idAluno;
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

    public boolean getIsConcluinte() { return isConcluinte; }
    public void setIsConcluinte(boolean isConcluinte) { this.isConcluinte = isConcluinte; }
	
}
