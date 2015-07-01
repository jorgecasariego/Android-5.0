package com.androidatc.viewpagerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmento6 extends Fragment {

    // Store instance variables
    private int pagina;
    private String titulo;

    public static final String ARG_PAGE = "page";
    public static final String TITULO = "title";

    // newInstance constructor for creating fragment with arguments
    public static Fragmento6 nuevaInstancia(int page, String title) {
        Fragmento6 fragmento6 = new Fragmento6();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(TITULO, title);
        fragmento6.setArguments(args);
        return fragmento6;
    }

    public Fragmento6() {
        // Required empty public constructor
    }

    //Dependiendo de los que hayan pasado al crear la instancia del fragment
    //guardar en las variables
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagina = getArguments().getInt(ARG_PAGE, 0);
        titulo = getArguments().getString(TITULO);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento6, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        tvLabel.setText(pagina + " -- " + titulo);

        return view;
    }


}
