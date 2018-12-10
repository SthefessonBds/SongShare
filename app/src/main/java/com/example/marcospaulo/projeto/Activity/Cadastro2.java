package com.example.marcospaulo.projeto.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.marcospaulo.projeto.DAO.ConfiguracaoFireBase;
import com.example.marcospaulo.projeto.Helper.Base64Custom;
import com.example.marcospaulo.projeto.Helper.Preferencias;
import com.example.marcospaulo.projeto.Model.Usuario;
import com.example.marcospaulo.projeto.R;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cadastro2 extends AppCompatActivity {

    EditText usuarioTxt, bioTxt, dataNascTxt, talentosTxt, generosTxt;
    RadioButton masculinoRb, femininoRb;
    Button btnConcluir;
    Usuario user;
    String nome, email, telefone, senha;
    CircleImageView photo_profile;
    private FirebaseAuth autenticacao;
    private ProgressDialog loadingBar;
    final static int Galery_Pick = 1;
    private StorageReference userProfileImageRef;
    String currentUserId;
    private DatabaseReference UserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);
        autenticacao = FirebaseAuth.getInstance();
        currentUserId = autenticacao.getCurrentUser().getUid();
        Intent intent = getIntent();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Usuario").child(currentUserId);
        if (intent != null) {
            Bundle parametros = intent.getExtras();
            if (parametros != null) {

                nome = parametros.getString("nome");
                email = parametros.getString("email");
                telefone = parametros.getString("telefone");
                senha = parametros.getString("senha");
            }
        }
        usuarioTxt = (EditText) findViewById(R.id.usuarioTxt);
        bioTxt = (EditText) findViewById(R.id.bioTxt);
        dataNascTxt = (EditText) findViewById(R.id.dataNascTxt);
        masculinoRb = (RadioButton) findViewById(R.id.masculinoRb);
        femininoRb = (RadioButton) findViewById(R.id.femininoRb);
        talentosTxt = (EditText) findViewById(R.id.talentosTxt);
        generosTxt = (EditText) findViewById(R.id.generosTxt);
        photo_profile = (CircleImageView) findViewById(R.id.select_image_profile);
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        loadingBar = new ProgressDialog(this);


        photo_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galeryIntent = new Intent();
                galeryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galeryIntent.setType("image/*");
                startActivityForResult(galeryIntent, Galery_Pick);
            }
        });

        btnConcluir = (Button) findViewById(R.id.btnConcluir);
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario, bio, dataNasc, sexo = "", talentos, generos;
                usuario = usuarioTxt.getText().toString();
                bio = bioTxt.getText().toString();
                dataNasc = dataNascTxt.getText().toString();
                talentos = talentosTxt.getText().toString();
                generos = generosTxt.getText().toString();

                if (masculinoRb.isChecked()) {
                    sexo = "masculino";
                }

                if (femininoRb.isChecked()) {
                    sexo = "feminino";
                }

                if (!usuario.equals("") && !bio.equals("") && !dataNasc.equals("") && !sexo.equals("") && !talentos.equals("") && !generos.equals("")) {
                    user = new Usuario();
                    user.setNome(nome);
                    user.setEmail(email);
                    user.setTelefone(telefone);
                    user.setSenha(senha);
                    user.setImagem("Nenhuma");
                    user.setUser(usuario);
                    user.setDescricao(bio);
                    user.setDatanasc(dataNasc);
                    user.setSeguindo("0");
                    user.setSeguidores("0");
                    user.setTalentos(talentos);
                    user.setGeneros(generos);


                    cadastrarUsuario();

                } else
                    Toast.makeText(getBaseContext(), "Preencha todos os dados", Toast.LENGTH_LONG).show();
            }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Galery_Pick && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (requestCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                StorageReference filePath = userProfileImageRef.child(currentUserId + ".jpg");

                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri downloadUrl = uri;
                        Toast.makeText(getBaseContext(), "Upload success! URL - " + downloadUrl.toString() , Toast.LENGTH_SHORT).show();
                        UserRef.child("profileimage").setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                        }
                });

            }
        }
    }


    public void cadastrarUsuario(){
        loadingBar.setTitle("Criando nova conta");
        loadingBar.setMessage("Afine seus instrumentos enquanto criamos sua nova conta...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(true);
        autenticacao = ConfiguracaoFireBase.getAutentication();
        autenticacao.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(Cadastro2.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loadingBar.dismiss();
                    Toast.makeText(getBaseContext(), "Usuário Cadastrado!", Toast.LENGTH_SHORT).show();
                    String identificadorUsuario = Base64Custom.codificarBase64(user.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    user.setId(identificadorUsuario);
                    user.salvar();


                    Preferencias preferenciasAndroid = new Preferencias(Cadastro2.this);
                    preferenciasAndroid.salvarUsuarioPreferencias(identificadorUsuario, user.getNome());
                    abrirLoginUsuario();

                } else {
                    loadingBar.dismiss();
                    String erroExcecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = " Digite uma senha mais forte, contendo no mínimo 8 caracteres de letras e números";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = " O e-mail digitado é inválido, digite um novo e-mail";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Esse e-mail já está cadastrado no sistema";
                    } catch (Exception e) {
                        erroExcecao = "Erro ao efetuar o cadastro!";
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastro2.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void abrirLoginUsuario() {
        Intent intent = new Intent(getBaseContext(), TelaLogin.class);
        startActivity(intent);
        finish();
    }

}
