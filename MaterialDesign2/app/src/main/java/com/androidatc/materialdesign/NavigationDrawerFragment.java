package com.androidatc.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "testPref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private InformationAdapter adapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSaveInstanceState;

    //Vista que usaremos para asociar con el NavigationDrawer
    private View containerView;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Obtenemos el valor booleano para saber si el usuario ya sabe que existe un navigationDrawer.
        //Si es verdadero entonces ya no hace abrir el naviagtionDrawer, si es falso le mostramos al usuario
        mUserLearnedDrawer = Boolean.valueOf(readToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));

        //Si saveInstanceState es distinto de null significa que vino de una ratacion de pantalla
        //En ese caso no queremos que muestre el navigationDrawer
        if(savedInstanceState != null){
            mFromSaveInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        //Asociamos el recyclerView con su ID
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new InformationAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return  layout;
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<Information>();
        int[] iconos = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.ic_number3, R.drawable.ic_number4};
        String[] titulos = {"Titulo 1","Titulo 2","Titulo 3","Titulo 4"};

        for (int i=0; i<titulos.length && i< iconos.length; i++){
            Information j = new Information(iconos[i], titulos[i]);
            data.add(j);
        }

        return  data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        //Asociamos el containerView al navigationDrawer que pasamos como parametro del metodo setUp()
        //luegoe ste containerView usaremos en el metodo openDrawer para abrir el NavigationDrawer cuando pulsamos sobre el icono
        containerView = getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                //SI el usuario nunca vio el drawer entonces esta es la primera vez que ve
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer +"");
                }
                //Con esto hacemos que el actionBar se dibuje de nuevo
                getActivity().invalidateOptionsMenu();
            }


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //Con esto hacemos que el actionBar se dibuje de nuevo
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Esto hacemos para que tenga un efecto de transparencia mientras abre el slide
                if(slideOffset < 0.6){
                    toolbar.setAlpha(1-slideOffset);
                }

            }
        };

        /**
         * La idea del NavigationDrawer es la siguiente: Si el usuario inicia por primera vez la aplicación
         * entonces le mostramos que existe un navigationDrawer. Si no no mostramos porque ya sabe que existe
         */
        if (!mUserLearnedDrawer && !mFromSaveInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        //El listener nos dice si el NavigationDrawer fue abierto o cerrado
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //Con esto hacemos que el navigationDrawe aparezca en la pantalla (icono hamburguesa)
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferencesName, String preferencesValues){
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(preferencesName, preferencesValues);
        editor.apply();
    }

    public static String readToPreferences(Context context, String preferencesName, String defaultValue){
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(preferencesName, defaultValue);
    }
}
