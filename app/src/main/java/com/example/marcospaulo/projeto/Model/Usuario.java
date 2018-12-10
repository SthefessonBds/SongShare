package com.example.marcospaulo.projeto.Model;

import com.example.marcospaulo.projeto.DAO.ConfiguracaoFireBase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuario {


    private String id;
    private String user;
    private String nome;
    private String sexo;
    private String datanasc;
    private String email;
    private String senha;
    private String descricao;
    private String imagem;
    private String telefone;
    private String seguidores;
    private String seguindo;
    private String generos;
    private String talentos;


    public Usuario(String user, String nome, String sexo, String datanasc, String email, String senha, String descricao, String imagem, String telefone, String seguidores, String seguindo) {
        this.user = user;
        this.nome = nome;
        this.sexo = sexo;

        this.datanasc = datanasc;
        this.email = email;
        this.senha = senha;
        this.descricao = descricao;
        this.imagem = imagem;
        this.telefone = telefone;
        this.seguidores = seguidores;
        this.seguindo = seguindo;
    }

    public Usuario() {
        this.user = "";
        this.nome = "";
        this.sexo = "";
        this.datanasc = "";
        this.email = "";
        this.senha = "";
        this.descricao = "";
        this.imagem = "";
        this.telefone = "";
        this.seguidores = "";
        this.seguindo = "";
        this.generos = "";
        this.talentos = "";
    }


    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getTalentos() {
        return talentos;
    }

    public void setTalentos(String talentos) {
        this.talentos = talentos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(String seguidores) {
        this.seguidores = seguidores;
    }

    public String getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(String seguindo) {
        this.seguindo = seguindo;
    }




    public void salvar(){
        DatabaseReference referenciaFirebase  = ConfiguracaoFireBase.getFirebase();
        referenciaFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);

    }


    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapUsuario = new HashMap<>();

        hashMapUsuario.put("id", getId());
        hashMapUsuario.put("nome", getNome());
        hashMapUsuario.put("email", getEmail());
        hashMapUsuario.put("telefone", getTelefone());
        hashMapUsuario.put("senha", getSenha());
        hashMapUsuario.put("imagem", getImagem());
        hashMapUsuario.put("user", getUser());
        hashMapUsuario.put("descricao", getDescricao());
        hashMapUsuario.put("dataNasc", getDatanasc());
        hashMapUsuario.put("sexo", getSexo());
        hashMapUsuario.put("seguindo", getSeguindo());
        hashMapUsuario.put("seguidores", getSeguidores());
        hashMapUsuario.put("generos", getGeneros());
        hashMapUsuario.put("talentos", getTalentos());

        return hashMapUsuario;

    }

}