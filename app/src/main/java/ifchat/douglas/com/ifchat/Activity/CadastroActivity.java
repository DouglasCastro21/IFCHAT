package ifchat.douglas.com.ifchat.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import ifchat.douglas.com.ifchat.DAO.Configuracao_Firebase;
import ifchat.douglas.com.ifchat.Entidades.ChatActivity;
import ifchat.douglas.com.ifchat.Entidades.Usuarios;
import ifchat.douglas.com.ifchat.Helper.Base64Custom;
import ifchat.douglas.com.ifchat.Helper.Preferencias;
import ifchat.douglas.com.ifchat.R;

public class CadastroActivity extends AppCompatActivity {

    protected ImageView spinnerImagem;
    private String camposIF[] = new String[] {"Selecione o Campus","Almenara","Araçuaí","Arinos","Diamantina","Janaúba","Januária","Montes Claros","Pirapora","Porteirinha","Salinas","Teófilo Otoni"};
    private Spinner spinner;
    private TextView txtcampus;





    private EditText  email;
    private EditText  confirmaremail;
    private EditText  senha;
    private EditText  confirmarsenha;


    private Usuarios usuarios;
    private Button botaocadastrar;
    private ProgressBar progressBar;
    private TextView criando;



    private FirebaseAuth autenticacao;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        elementos();



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){

                    txtcampus.setVisibility(View.GONE);

                } else if(position == 1){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Almenara");

                }else if (position==2){
                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Araçuaí");

                }else if(position==3){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Arinos");


                }else if (position==4){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Diamantina");

                }else if (position==5){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Janaúba");

                }else if (position==6){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Januária");

                }else if (position==7){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Montes Claros");


                }else if (position==8){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Pirapora");

                }else if (position==9){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Porteirinha");

                }else if (position==10){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Salinas");


                }else if (position==11){

                    txtcampus.setVisibility(View.VISIBLE);
                    txtcampus.setText("Campus "+ "Teófilo Otoni");


                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                criando.setVisibility(View.VISIBLE);

                if (!email.getText().toString().equals("") &&
                        !confirmaremail.getText().toString().equals("") && !senha.getText().toString().equals("") &&
                        !confirmarsenha.getText().toString().equals("")){


                    if (senha.getText().toString().equals(confirmarsenha.getText().toString())) {
                        if (email.getText().toString().equals(confirmaremail.getText().toString())) {



                            inicializarElementos();
                            cadastrarUsuario();



                        }else{

                            Toast.makeText(CadastroActivity.this, "Os E-mail não são correspondentes", Toast.LENGTH_LONG).show();
                            email.requestFocus();
                            progressBar.setVisibility(View.GONE);
                            criando.setVisibility(View.GONE);


                        }

                    } else {


                        Toast.makeText(CadastroActivity.this, "As senhas não são correspondentes", Toast.LENGTH_LONG).show();
                        senha.requestFocus();
                        progressBar.setVisibility(View.GONE);
                        criando.setVisibility(View.GONE);

                    }

                }else {


                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);
                    criando.setVisibility(View.GONE);


                }
            }

        });





    }


    public void elementos(){

        //carrega os spinner
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CadastroActivity.this, android.R.layout.simple_spinner_dropdown_item, camposIF);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerImagem = (ImageView) findViewById(R.id.imageViewSpinnerID);
        spinner = (Spinner) findViewById(R.id.spinnerID);
        spinner.setAdapter(arrayAdapter);
        txtcampus = (TextView) findViewById(R.id.textViewCampusID);

        email = (EditText)findViewById(R.id.EmailtextID);
        confirmaremail = (EditText)findViewById(R.id.confirmarEmailID);
        senha = (EditText)findViewById(R.id.confirmarSenhaID);
        confirmarsenha = (EditText)findViewById(R.id.confirmarSenhaID);
        botaocadastrar = (Button) findViewById(R.id.buttonCadastrarUsuarioID);

        progressBar = (ProgressBar)findViewById(R.id.progressBarCdastroID);

        criando     = (TextView) findViewById(R.id.criandoID);



    }


    private void inicializarElementos(){

        usuarios = new Usuarios();

        usuarios.setEmail(email.getText().toString());
        usuarios.setSenha(senha.getText().toString());

    }



    private void cadastrarUsuario(){

        autenticacao = Configuracao_Firebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()


        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {




                if (task.isSuccessful()){

                    progressBar.setVisibility(View.GONE);

                    criando.setVisibility(View.GONE);

                    Toast.makeText(CadastroActivity.this,"Usuário cadastrado com sucesso!",Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setIdUsuario(identificadorUsuario);
                    usuarios.Salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario,usuarios.getEmail());



                    abrirAreaUsuario();

                }else{
                    progressBar.setVisibility(View.GONE);
                    String erroExcecao = "";

                    try {
                        throw  task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){

                        erroExcecao = "Digite uma senha contendo no mínimo 8 caracteres entre letras e numeros";
                        senha.requestFocus();
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Email já cadastrado   ";
                        email.requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "O campo de email está mal formado  ";
                        email.requestFocus();

                    } catch (Exception e){
                        erroExcecao = "Erro ao efetuar cadastro,verifique a Conexão com a internet";
                        e.printStackTrace();

                    }




                    Toast.makeText(CadastroActivity.this,"Erro : " + erroExcecao,Toast.LENGTH_LONG ).show();

                }
            }
        });



    }


    private void abrirAreaUsuario() {


        Intent intent = new Intent(CadastroActivity.this ,ChatActivity.class);
        startActivity(intent);
        finish();


    }



}
