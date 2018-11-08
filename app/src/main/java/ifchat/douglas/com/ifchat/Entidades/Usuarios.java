package ifchat.douglas.com.ifchat.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import ifchat.douglas.com.ifchat.DAO.Configuracao_Firebase;

public class Usuarios {


    private String idUsuario;
    private String email;
    private String senha;


    public Usuarios() {

    }


    // salvar usuarios
    public void Salvar(){

        DatabaseReference referenciaFirebase = Configuracao_Firebase.getFirebase();
        referenciaFirebase.child("Usuarios").child(String.valueOf(getIdUsuario())).setValue(this);

    }


    @Exclude
    public Map<String , Object> toMap() {
        HashMap<String , Object> hashMapUsuario = new HashMap<>();

        hashMapUsuario.put("id",getIdUsuario());
        hashMapUsuario.put("email",getEmail());
        hashMapUsuario.put("senha",getSenha());



        return hashMapUsuario;

    }





    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


