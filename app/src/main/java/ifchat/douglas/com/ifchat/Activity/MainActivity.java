package ifchat.douglas.com.ifchat.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ifchat.douglas.com.ifchat.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private TextView buttonCadastro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
