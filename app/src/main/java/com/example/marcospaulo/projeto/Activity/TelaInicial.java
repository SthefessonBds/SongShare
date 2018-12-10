package com.example.marcospaulo.projeto.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.marcospaulo.projeto.R;




public class TelaInicial extends AppCompatActivity {

    Button btnComecar;

    public void irParaMenuLogin(View v) {
        Intent inte = new Intent(getApplicationContext(), TelaMenuLogin.class);
        startActivity(inte);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        btnComecar = (Button) findViewById(R.id.btnComecar);

    }
}