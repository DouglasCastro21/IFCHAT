package ifchat.douglas.com.ifchat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ifchat.douglas.com.ifchat.Helper.Preferencias;
import ifchat.douglas.com.ifchat.Model.Mensagem;
import ifchat.douglas.com.ifchat.R;

public class AdapterMensagem  extends ArrayAdapter<Mensagem>{


private Context context;
private ArrayList<Mensagem> mensagens;

    public AdapterMensagem(@NonNull Context c, @NonNull ArrayList<Mensagem> objects) {

        super(c, 0, objects);
        this.context=c;
        this.mensagens=objects;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

      // verifica se a lista foi preenchida
        if (mensagens !=null){


            Preferencias preferencias = new Preferencias(context);
            String idUsuarioRemetente = preferencias.getIdentificador();


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


           Mensagem mensagem = mensagens.get(position);


          // montar a lista


            if (idUsuarioRemetente.equals(mensagem.getIdUsuario())){

                view = inflater.inflate(R.layout.item_mensagem_direito,parent,false);


            }else{


                view = inflater.inflate(R.layout.item_mensagem_esquerda,parent,false);



            }




        // recuperar elemnto para exibição



            TextView textoMensagem = (TextView) view.findViewById(R.id.tv_mensagem);
            textoMensagem.setText(mensagem.getMensagem());


        }


        return view;
    }
}
