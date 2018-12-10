package com.example.marcospaulo.projeto.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.marcospaulo.projeto.DAO.ConfiguracaoFireBase;
import com.example.marcospaulo.projeto.Model.Usuario;
import com.example.marcospaulo.projeto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaLogin extends AppCompatActivity {

    private EditText emailTxt, senhaTxt;
    private Button btnLogin;
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailTxt = (EditText) findViewById(R.id.emailTxt);
        senhaTxt = (EditText) findViewById(R.id.senhaTxt);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        loadingBar = new ProgressDialog(this);
    }

    public void logar(View v){
       if(!emailTxt.getText().toString().equals("") && !senhaTxt.getText().toString().equals("")){
           usuario = new Usuario();
           usuario.setEmail((emailTxt.getText().toString()));
           usuario.setSenha(senhaTxt.getText().toString());
           validarLogin();
       }else

        Toast.makeText(getBaseContext(), "Preencha os campos de e-mail e senha", 10).show();
    }



    private void validarLogin(){
        loadingBar.setTitle("Fazendo Login");
        loadingBar.setMessage("Aqueça sua voz enquanto efetuamos o login");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(true);
        autenticacao = ConfiguracaoFireBase.getAutentication();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loadingBar.dismiss();
                    Toast.makeText(getBaseContext(), "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), TelaPrincipal.class);
                    emailTxt.setText("");
                    senhaTxt.setText("");
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
