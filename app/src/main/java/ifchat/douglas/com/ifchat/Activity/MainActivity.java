package ifchat.douglas.com.ifchat.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import ifchat.douglas.com.ifchat.DAO.Configuracao_Firebase;
import ifchat.douglas.com.ifchat.Entidades.ChatActivity;
import ifchat.douglas.com.ifchat.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private TextView buttonCadastro;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase firebaseDatabase = Configuracao_Firebase.getFireb();

        verificarUsuarioLogado();

        buttonLogin = (Button) findViewById(R.id.buttonLoginID);
        buttonCadastro = (TextView) findViewById(R.id.buttonCadastrarUsuarioID);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });



        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });






    }




    public void abrirTElaPrincipal() {


        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "Bem Vindo!", Toast.LENGTH_LONG).show();


    }


    private void verificarUsuarioLogado() {

        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();

        if (autenticacao.getCurrentUser() != null) {

            abrirTElaPrincipal();
            finish();

        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
