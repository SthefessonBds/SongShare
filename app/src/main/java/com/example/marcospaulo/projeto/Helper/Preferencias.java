package com.example.marcospaulo.projeto.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    private Context context;
    private SharedPreferences preferencias;

    private String nome_arquivo = "SongShareFirebase.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "verificarUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";

    public Preferencias(Context context) {
        this.context = context;
        preferencias = context.getSharedPreferences(nome_arquivo, MODE);
        editor = preferencias.edit();
    }

    public void salvarUsuarioPreferencias(String identificadorUsuario, String nomeUsuario){
        editor.putString(CHAVE_IDENTIFICADOR, identificadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferencias.getString("CHAVE_IDENTIFICADOR", null);
    }

    public String getNome(){
        return preferencias.getString("CHAVE_NOME", null);
    }
}
