package com.example.marcospaulo.projeto.Model;

public class Faixa {
    private String codigo;
    private Usuario usuario;
    private String nome;
    private String icone;
    private String duracao;
    private String genero;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Faixa(String codigo, Usuario usuario, String nome, String icone, String duracao, String genero) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.nome = nome;
        this.icone = icone;
        this.duracao = duracao;
        this.genero = genero;
    }

    public Faixa() {
        this.codigo = "";
        this.usuario = null;
        this.nome = "";
        this.icone = "";
        this.duracao = "";
        this.genero = "";
    }



}


