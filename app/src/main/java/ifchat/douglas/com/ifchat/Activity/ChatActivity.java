package ifchat.douglas.com.ifchat.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ifchat.douglas.com.ifchat.DAO.Configuracao_Firebase;
import ifchat.douglas.com.ifchat.Fragmentos.JanuariaFragment;
import ifchat.douglas.com.ifchat.Helper.Preferencias;
import ifchat.douglas.com.ifchat.Model.Mensagem;
import ifchat.douglas.com.ifchat.R;

public class ChatActivity extends AppCompatActivity {


    private FirebaseAuth usuarioFirebase;
    private DatabaseReference firebase;
    private android.support.v7.widget.Toolbar toolbarr;


    private EditText editMensagem;
    private ImageButton btnEnviar;
    private ListView listView;
    private ArrayList<String> mensagens;
    private ArrayAdapter adapter;
    private ValueEventListener valueEventListenerMensagens;

    private String idUsuarioRemetente;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        editMensagem = (EditText) findViewById(R.id.caixaMensagemID);
        btnEnviar = (ImageButton) findViewById(R.id.btnEnviarMensagem);
        listView = (ListView) findViewById(R.id.listViewConversasID);


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        JanuariaFragment januariaFragment= new JanuariaFragment();

        fragmentTransaction.add(R.id.containerFragmentID,januariaFragment);

        fragmentTransaction.commit();



        toolbarr = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar3) ;
        setTitle("");
        setSupportActionBar(toolbarr);

        usuarioFirebase = Configuracao_Firebase.getFirebaseAutenticacao();

        // recuperar dados do usuario logado
        Preferencias preferencias = new Preferencias(ChatActivity.this);

       idUsuarioRemetente = preferencias.getIdentificador();


       // Montar o lista de Mensagens

        mensagens = new ArrayList<>();
        adapter = new ArrayAdapter(

                ChatActivity.this,
                android.R.layout.activity_list_item,mensagens


        );

        listView.setAdapter(adapter);

        //recuperar mensagens do Firebase


        firebase = Configuracao_Firebase.getFirebase().child("Mensagens").child(idUsuarioRemetente);


         // Criar listenner para mensagens

        valueEventListenerMensagens = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // limpa mensagens
                 mensagens.clear();


                // recuperando mensagens
                for (DataSnapshot dados : dataSnapshot.getChildren()){

                Mensagem mensagem = dados.getValue(Mensagem.class);

                mensagens.add(mensagem.getMensagem());


                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        firebase.addValueEventListener(valueEventListenerMensagens);





        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            String textoMensagem = editMensagem.getText().toString();


            if (textoMensagem.isEmpty()){


                Toast.makeText(ChatActivity.this, "Digite uma mensagem para enviar!", Toast.LENGTH_SHORT).show();



            }else{


                Mensagem mensagem = new Mensagem();
                mensagem.setIdUsuario(idUsuarioRemetente);
                mensagem.setMensagem(textoMensagem);


                salvarMensagens(idUsuarioRemetente,mensagem);


            }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


      getMenuInflater().inflate(R.menu.main2,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        switch (id){

            case R.id.action_sair:


            caixaDialogoSair();

        }

        return super.onOptionsItemSelected(item);

    }



    private boolean salvarMensagens(String idRemetente, Mensagem mensagem){


        try {

            firebase = Configuracao_Firebase.getFirebase().child("Mensagens");
            firebase.child(idRemetente).push().setValue(mensagem);

            return true;
        }catch (Exception e){

            e.printStackTrace();
            return false;


        }


    }


    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMensagens);
    }
}
