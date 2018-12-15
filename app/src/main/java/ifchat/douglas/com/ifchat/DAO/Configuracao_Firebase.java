package ifchat.douglas.com.ifchat.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class Configuracao_Firebase {

    private  static DatabaseReference referenciaFirebase;
    private  static FirebaseAuth autenticacao;
    private  static FirebaseDatabase firebaseDatabase;





    public static DatabaseReference getFirebase(){


        if (referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();


        }

        return referenciaFirebase;
    }




    //persistencia

    public static  FirebaseDatabase getFireb(){

        if(firebaseDatabase ==null){
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);




        }

        return firebaseDatabase;


    }



    public static FirebaseAuth getFirebaseAutenticacao(){

        if (autenticacao == null){

            autenticacao = FirebaseAuth.getInstance();
        }
            return autenticacao;
    }

}
