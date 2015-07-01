package com.androidatc.viewpagerexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ScreenSlidePageFragment extends Fragment {
    /**
     * 1. page será utilizado como clave para identificar el numero de pagina que representa el fragment
     */
    public static final String ARG_PAGE = "page";

    /**
     * 2. mPageNumber es el numero de pagina del fragment, el cual es seteado a
     * partir del valor de {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * 3. Metodo utilizado para crear un Fragment del tipo ScreenSlidePageFragment
     * a partir del numero de pagina recibido como argumento.
     * Guardamos el valor (numero de pagina) en {@link #ARG_PAGE}.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //4. Obtenemos el numero de pagina a partir de la clave
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }


    /**
     * 5. Creamos la vista utilizando como layout la vista que tiene texto lorem_ipsu
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        // 6. Seteamos el titulo de la vista para mostrar el numero de pagina
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText("Fragmento: " + mPageNumber);

        //((TextView) view.findViewById(android.R.id.text1)).setText(
        //       getString(R.string.title_template_step, mPageNumber + 1));

        return view;
    }

    /**
     * 7. Retornamos el numero de la pagina representado por este fragment
     */
    public int getPageNumber() {
        return mPageNumber;
    }

}
