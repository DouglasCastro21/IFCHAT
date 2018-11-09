package ifchat.douglas.com.ifchat.Entidades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ifchat.douglas.com.ifchat.Activity.MainActivity;
import ifchat.douglas.com.ifchat.DAO.Configuracao_Firebase;
import ifchat.douglas.com.ifchat.R;

public class ChatActivity extends AppCompatActivity {

    private ImageButton toolbar1Button;
    private FirebaseAuth usuarioFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();
        toolbar1Button = (ImageButton)findViewById(R.id.toolbar1ButtonID);


        toolbar1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final CharSequence[] opcoes = {"Mudar status da bike", "Denunciar", "Sair"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                builder.setTitle("");
                builder.setItems(opcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {



                        if (opcoes[i].equals("Configurações")) {





                        } else if (opcoes[i].equals("Denunciar")) {





                        } else if (opcoes[i].equals("Sair")) {



                          caixaDialogoSair();

                        }

                    }
                });
                builder.show();

            }
        });




    }


    public void deslogarUsuario(){


        usuarioFirebase.signOut();
        Intent intent = new Intent(ChatActivity.this ,MainActivity.class);
        startActivity(intent);
        finish();

    }


    private void caixaDialogoSair(){

        AlertDialog.Builder alertaDialog = new AlertDialog.Builder(ChatActivity.this);

        // configurando dialogo

        alertaDialog.setTitle("Sair");
        alertaDialog.setIcon(R.drawable.ic_action_warning);

        alertaDialog.setMessage("Deseja realmente sair ? ");
        alertaDialog.setCancelable(false);


        //conf botões
        alertaDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deslogarUsuario();


            }
        });

        alertaDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertaDialog.create();
        alertaDialog.show();

    }
}
