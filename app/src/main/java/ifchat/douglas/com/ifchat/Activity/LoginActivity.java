package ifchat.douglas.com.ifchat.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ifchat.douglas.com.ifchat.DAO.Configuracao_Firebase;
import ifchat.douglas.com.ifchat.Model.Usuarios;
import ifchat.douglas.com.ifchat.R;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth autenticacao;
    private Usuarios usuarios;
    private ProgressBar progressBar;
    private TextView carregando;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        verificarUsuarioLogado();

        final TextView edtEmail = (TextView) findViewById(R.id.LoginEmailID);
        final TextView edtSenha = (TextView) findViewById(R.id.LoginSenhaID);
        progressBar = (ProgressBar) findViewById(R.id.LoginProgressBarID);
        carregando  = (TextView)findViewById(R.id.LoginCarregandoID);



        Button btnLogar = (Button)findViewById(R.id.LogarID);



        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {


                    usuarios = new Usuarios();

                    usuarios.setEmail(edtEmail.getText().toString());
                    usuarios.setSenha(edtSenha.getText().toString());

                    progressBar.setVisibility(View.VISIBLE);
                    carregando.setVisibility(View.VISIBLE);
                    validarLogin();

                } else {

                    Toast.makeText(LoginActivity.this, "Preencha os campos de E-mail e senha", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    carregando.setVisibility(View.GONE);
                }

            }


        });



    }



    private void validarLogin(){

        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()

        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){
                    LoginActivity.this.finish();

                    abrirTElaPrincipal();


                    Toast.makeText(LoginActivity.this,"Login Efetuado com Sucesso", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this,"Bem Vindo!", Toast.LENGTH_SHORT).show();


                }else {

                    Toast.makeText(LoginActivity.this, "Usuário ou senha invalidos", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    carregando.setVisibility(View.GONE);
                }

            }
        });


    }


    public void abrirTElaPrincipal(){


        Intent intent = new Intent(this,ChatActivity.class);
        startActivity(intent);

    }



    private  void verificarUsuarioLogado() {
        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {

            abrirTElaPrincipal();



        }
    }




}
