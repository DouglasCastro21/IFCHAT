package ifchat.douglas.com.ifchat.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ifchat.douglas.com.ifchat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MontesClarosFragment extends Fragment {


    public MontesClarosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view  =  inflater.inflate(R.layout.fragment_montes_claros, container, false);
        return view;
    }

}
