package com.example.marcospaulo.projeto.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.marcospaulo.projeto.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TelaPerfil extends AppCompatActivity {

    private TextView status;
    private TextView nome, username, bio, talentos, generos, seguindo, seguidores;
    private CircleImageView imagem_perfil_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);
        nome = findViewById(R.id.nome_perfil_edit);
        username = findViewById(R.id.user_perfil_edit);
        bio = findViewById(R.id.user_perfil_edit);
        talentos = findViewById(R.id.talentos_perfil_edit);
        generos = findViewById(R.id.generos_perfil_edit);
        seguindo = findViewById(R.id.seguindo_perfil_edit);
        seguidores = findViewById(R.id.seguidores_perfil_edit);
        imagem_perfil_edit = findViewById(R.id.foto_perfil_edit);

        status = (TextView) findViewById(R.id.text_status);

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getBaseContext(), Status.class);
                startActivity(intent);
            }
        });
    }
}
