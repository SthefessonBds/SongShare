package com.example.marcospaulo.projeto.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.marcospaulo.projeto.R;

public class TelaMenuLogin extends AppCompatActivity {

    Button btnLogin, btnCadastrar;

            public void fazerLogin(View v) {
               Intent inte = new Intent(getApplicationContext(), TelaLogin.class);
               startActivity(inte);
            }

    public void fazerCadastro(View v) {
        Intent inte = new Intent(getApplicationContext(), Cadastro1.class);
        startActivity(inte);
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuescolha);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);



    }
}