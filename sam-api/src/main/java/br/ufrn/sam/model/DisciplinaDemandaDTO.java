package br.ufrn.sam.model;

public class DisciplinaDemandaDTO {
    private String codigo;
    private String nome;
    private Long quantidadeInteressados;

    public DisciplinaDemandaDTO(String codigo, String nome, Long quantidadeInteressados) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidadeInteressados = quantidadeInteressados;
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getQuantidadeInteressados() {
        return quantidadeInteressados;
    }

    public void setQuantidadeInteressados(Long quantidadeInteressados) {
        this.quantidadeInteressados = quantidadeInteressados;
    }
}