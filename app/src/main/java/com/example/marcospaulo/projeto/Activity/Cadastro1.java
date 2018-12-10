package com.example.marcospaulo.projeto.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcospaulo.projeto.R;

public class Cadastro1 extends AppCompatActivity {

    EditText nomeTxt, emailTxt, telefoneTxt, senhaTxt, confirmarSenhaTxt;
    private Button CreateAccountButton;

    public void irParaCadastro2(View v) {

        String nome, email, telefone, senha, confirmarSenha;

        nome = nomeTxt.getText().toString();
        email = emailTxt.getText().toString();
        telefone = telefoneTxt.getText().toString();
        senha = senhaTxt.getText().toString();
        confirmarSenha  = confirmarSenhaTxt.getText().toString();
        if(!nome.equals("")&&!email.equals("")&&!telefone.equals("")&&!senha.equals("")&&!confirmarSenha.equals("")) {
            if(senha.equals(confirmarSenha)) {
                Intent inte = new Intent(getApplicationContext(), Cadastro2.class);
                Bundle parametros = new Bundle();
                parametros.putString("nome", nome);
                parametros.putString("email", email);
                parametros.putString("telefone", telefone);
                parametros.putString("senha", senha);

                inte.putExtras(parametros);
                startActivity(inte);
            }else{
                Toast.makeText(getBaseContext(), "Senhas diferentes", 10).show();
            }
        }else{
            Toast.makeText(getBaseContext(), "Preencha todos os campos", 10).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);
        nomeTxt = (EditText) findViewById(R.id.nomeTxt);
        emailTxt = (EditText) findViewById(R.id.emailTxt);
        telefoneTxt = (EditText) findViewById(R.id.telefoneTxt);
        senhaTxt = (EditText) findViewById(R.id.senhaTxt);
        confirmarSenhaTxt = (EditText) findViewById(R.id.confirmarSenhaTxt);
    }


}
